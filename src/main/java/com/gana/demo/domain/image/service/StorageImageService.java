package com.gana.demo.domain.image.service;

import com.gana.demo.domain.image.Image;
import com.gana.demo.domain.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class StorageImageService implements ImageService {
    private final ImageRepository imageRepository;

    @Value("${file.upload.folder:file/upload}")
    private String uploadFolderPath;

    private Path uploadFolder;

    @PostConstruct
    public void init() throws IOException {
        uploadFolder = Paths.get(uploadFolderPath);

        if (!Files.exists(uploadFolder)) {
            if (!uploadFolder.toFile().mkdirs()) {
                log.error("파일 임시 업로드 폴더 생성을 실패하였습니다. (경로 : {})", uploadFolder.toAbsolutePath());
                throw new IOException("파일 임시 업로드 폴더 생성을 실패하였습니다.");
            } else {
                log.info("파일 임시 업로드 폴더 생성하였습니다. (경로 : {})", uploadFolder.toAbsolutePath());
            }
        } else {
            if (!Files.isDirectory(uploadFolder)) {
                log.error("파일 임시 업로드 경로가 폴더가 아닙니다. (경로 : {})", uploadFolder.toAbsolutePath());
                throw new IOException("파일 임시 업로드 저장 경로가 폴더가 아닙니다.");
            } else {
                log.info("파일 임시 업로드 폴더가 이미 있습니다. (경로 : {})", uploadFolder.toAbsolutePath());
            }
        }
    }

    @Transactional
    @Override
    public Image upload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("Failed to store empty file.");
        }

        String originName = file.getOriginalFilename();
        String format = originName.substring(originName.lastIndexOf(".") + 1);

        if (!checkFormat(format)) {
            throw new RuntimeException("file format error");
        }

        String uuid = UUID.randomUUID().toString();
        String name = String.format("%s.%s", uuid, format);
        String path = getSaveFolderPath();

        Path todayFolder = this.uploadFolder.resolve(path);

        makeOrLoadFolder(todayFolder);

        Path filePath = todayFolder.resolve(Paths.get(name));

        file.transferTo(filePath);

        return imageRepository.save(Image.create(name, originName, path));
    }

    @Override
    public Resource serve(String path) throws IOException {
        Path file = uploadFolder.resolve(path);

        try {
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException(file.getFileName().toString());
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException(file.getFileName().toString());
        }
    }

    private boolean checkFormat(String format) {
        // 추후 구현
        return !format.isEmpty();
    }

    private String getSaveFolderPath() {
        LocalDateTime now = LocalDateTime.now();

        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        return String.format("%d/%d/%d", year, month, day);
    }

    private void makeOrLoadFolder(Path path) throws IOException {
        if (!Files.exists(path)) {
            if (!path.toFile().mkdirs()) {
                log.error("폴더 생성을 실패하였습니다. (경로 : {})", path.toAbsolutePath());
                throw new IOException("폴더 생성을 실패하였습니다.");
            } else {
                log.info("폴더 생성하였습니다. (경로 : {})", path.toAbsolutePath());
            }
        } else {
            if (!Files.isDirectory(path)) {
                log.error("경로가 폴더가 아닙니다. (경로 : {})", path.toAbsolutePath());
                throw new IOException("경로가 폴더가 아닙니다.");
            } else {
                log.info("폴더가 이미 있습니다. (경로 : {})", path.toAbsolutePath());
            }
        }
    }
}
