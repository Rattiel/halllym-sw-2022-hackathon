package com.gana.demo.domain.shopping.mall.exception;

import com.gana.demo.domain.common.exception.EntityNotFoundException;
import lombok.Getter;

@Getter
public class ShoppingMallNotFoundException extends EntityNotFoundException {
    private static final String REASON = "카테고리가 존재하지 않습니다.";

    public ShoppingMallNotFoundException() {
        super(REASON, REASON);
    }
}
