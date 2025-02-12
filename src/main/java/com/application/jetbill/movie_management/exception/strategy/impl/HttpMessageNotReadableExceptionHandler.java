package com.application.jetbill.movie_management.exception.strategy.impl;

import com.application.jetbill.movie_management.dto.response.ApiError;
import com.application.jetbill.movie_management.exception.strategy.ExceptionHandlerStrategy;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class HttpMessageNotReadableExceptionHandler implements ExceptionHandlerStrategy {
    @Override
    public ResponseEntity<ApiError> handleException(Exception exception,
                                                    HttpServletRequest request,
                                                    LocalDateTime timestamps) {
        int statusCode = HttpStatus.BAD_REQUEST.value();
        ApiError apiError = new ApiError(
                statusCode,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Oops! Error reading the HTTP message body. "+"Make sure the request is correctly formatted"
                +"and contains valid data.",
                exception.getMessage(),
                timestamps,null

        );
        return ResponseEntity.status(statusCode).body(apiError);
    }
}
