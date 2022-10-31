package com.gana.demo.domain.common.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public final class MessageResponse {
    private int status;
    private String message;
}
