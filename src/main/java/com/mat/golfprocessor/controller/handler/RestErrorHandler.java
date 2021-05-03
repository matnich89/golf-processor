package com.mat.golfprocessor.controller.handler;

import com.mat.golfprocessor.domain.dto.error.ErrorDto;
import com.mat.golfprocessor.exception.InvalidIdException;
import com.mat.golfprocessor.exception.convertor.DateMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidIdException.class})
    protected ResponseEntity<ErrorDto> handleNotFound(RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildError(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler({DateMismatchException.class})
    protected ResponseEntity<ErrorDto> handleBadRequest(RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildError(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    private ErrorDto buildError(String message, Integer statusCode) {
       return ErrorDto.builder().statusCode(statusCode).message(message).timestamp(LocalDateTime.now()).build();
    }
}
