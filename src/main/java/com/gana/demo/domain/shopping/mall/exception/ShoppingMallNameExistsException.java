package com.gana.demo.domain.shopping.mall.exception;

import com.gana.demo.domain.common.exception.FieldException;

public class ShoppingMallNameExistsException extends FieldException {
    private static final String REASON = "이미 같은 이름의 쇼핑몰이 있습니다.";

    public ShoppingMallNameExistsException(String msg) {
        super(msg, "name", REASON);
    }
}
