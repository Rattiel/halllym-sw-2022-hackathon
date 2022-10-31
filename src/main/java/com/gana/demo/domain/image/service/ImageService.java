package com.gana.demo.domain.image.service;

import com.gana.demo.domain.image.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image upload(MultipartFile file) throws IOException;

    Resource serve(String path) throws IOException;
}
