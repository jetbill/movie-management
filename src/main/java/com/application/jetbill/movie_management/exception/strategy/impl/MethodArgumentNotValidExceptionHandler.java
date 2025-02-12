package com.application.jetbill.movie_management.exception.strategy.impl;

import com.application.jetbill.movie_management.dto.response.ApiError;
import com.application.jetbill.movie_management.exception.strategy.ExceptionHandlerStrategy;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.List;

public class MethodArgumentNotValidExceptionHandler implements ExceptionHandlerStrategy {
    @Override
    public ResponseEntity<ApiError> handleException(Exception exception, HttpServletRequest request, LocalDateTime timestamps) {

        MethodArgumentNotValidException validationException = (MethodArgumentNotValidException) exception;

        int httpStatusCode = HttpStatus.BAD_REQUEST.value();
        List<ObjectError> errors = validationException.getAllErrors();
        List<String> details = errors.stream().map(error -> {
            if (error instanceof FieldError fieldError) {
                return fieldError.getField() + ": " + fieldError.getDefaultMessage();
            }
            return error.getDefaultMessage();
        }).toList();
        ApiError apiError = new ApiError(
                httpStatusCode,
                request.getRequestURL().toString(),
                request.getMethod(),
                "The request contains invalid or incomplete parameters."+
                "Please verified and provide de required information before trying again.",
                validationException.getMessage(),
                timestamps,
                details
        );
        return ResponseEntity.status(httpStatusCode).body(apiError);
    }
}
