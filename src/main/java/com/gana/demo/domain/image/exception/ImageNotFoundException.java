package com.gana.demo.domain.image.exception;

import com.gana.demo.domain.common.exception.EntityNotFoundException;
import lombok.Getter;

@Getter
public class ImageNotFoundException extends EntityNotFoundException {
    private static final String REASON = "이미지가 존재하지 않습니다.";

    public ImageNotFoundException(Long id) {
        super(REASON + "(id : " + id + ")", REASON);
    }
}
