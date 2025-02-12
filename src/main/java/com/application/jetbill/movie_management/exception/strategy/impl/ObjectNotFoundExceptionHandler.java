package com.application.jetbill.movie_management.exception.strategy.impl;

import com.application.jetbill.movie_management.dto.response.ApiError;
import com.application.jetbill.movie_management.exception.ObjectNotFoundException;
import com.application.jetbill.movie_management.exception.strategy.ExceptionHandlerStrategy;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ObjectNotFoundExceptionHandler implements ExceptionHandlerStrategy {
    @Override
    public ResponseEntity<ApiError> handleException(Exception exception, HttpServletRequest request,
                                                    LocalDateTime timestamps) {
        ObjectNotFoundException objectNotFoundException = (ObjectNotFoundException) exception;

        int httpStatusCode = HttpStatus.NOT_FOUND.value();

        ApiError apiError = new ApiError(
                httpStatusCode,
                request.getRequestURL().toString(),
                request.getMethod(),
                "I'm sorry, request petition could not be found." +
                        "Please check the URL or try another search",
                objectNotFoundException.getMessage(),
                timestamps,
                null
        );
        return ResponseEntity.status(httpStatusCode).body(apiError);
    }
}
