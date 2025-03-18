package com.application.jetbill.movie_management.exception.strategy.impl;

import com.application.jetbill.movie_management.dto.response.ApiError;
import com.application.jetbill.movie_management.exception.strategy.ExceptionHandlerStrategy;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class HttpRequestMethodNotSupportedExceptionHandler implements ExceptionHandlerStrategy {
    @Override
    public ResponseEntity<ApiError> handleException(Exception exception,
                                                    HttpServletRequest request,
                                                    LocalDateTime timestamps) {
        int httpStatusCode = HttpStatus.METHOD_NOT_ALLOWED.value();

        ApiError apiError = new ApiError(
            httpStatusCode,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Oops! method not allowed. Check the HTTP method or request URL",
                exception.getMessage(),
                timestamps,
                null
        );

        return ResponseEntity.status(httpStatusCode).body(apiError);
    }
}
