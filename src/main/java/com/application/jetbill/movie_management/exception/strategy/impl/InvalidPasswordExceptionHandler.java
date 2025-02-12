package com.application.jetbill.movie_management.exception.strategy.impl;

import com.application.jetbill.movie_management.dto.response.ApiError;
import com.application.jetbill.movie_management.exception.InvalidPasswordException;
import com.application.jetbill.movie_management.exception.strategy.ExceptionHandlerStrategy;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class InvalidPasswordExceptionHandler implements ExceptionHandlerStrategy {
    @Override
    public ResponseEntity<ApiError> handleException(Exception exception, HttpServletRequest request,
                                                    LocalDateTime timestamps) {
        InvalidPasswordException invalidPasswordException = (InvalidPasswordException) exception;
        int httpStatusCode = HttpStatus.BAD_REQUEST.value();

        ApiError apiError = new ApiError(
                httpStatusCode,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Invalid password: the provided password does not meet the required criteria "+
                invalidPasswordException.getPassword(),
                invalidPasswordException.getMessage(),
                timestamps,
                null
        );
        return ResponseEntity.status(httpStatusCode).body(apiError);
    }

}
