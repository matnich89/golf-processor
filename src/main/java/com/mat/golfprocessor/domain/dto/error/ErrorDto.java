package com.mat.golfprocessor.domain.dto.error;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class ErrorDto {
    LocalDateTime timestamp;
    String message;
    Integer statusCode;
}
