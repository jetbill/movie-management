package com.application.jetbill.movie_management.exception.strategy.impl;

import com.application.jetbill.movie_management.dto.response.ApiError;
import com.application.jetbill.movie_management.exception.strategy.ExceptionHandlerStrategy;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class MethodArgumentTypeMismatchExceptionHandler implements ExceptionHandlerStrategy {
    @Override
    public ResponseEntity<ApiError> handleException(Exception exception, HttpServletRequest request,
                                                    LocalDateTime timestamps) {

        MethodArgumentTypeMismatchException argumentException = (MethodArgumentTypeMismatchException) exception;
        int httpStatusCode = HttpStatus.BAD_REQUEST.value();
        Object valuerRejected = argumentException.getValue();
        String propertyName = argumentException.getName();
        ApiError apiError = new ApiError(
                httpStatusCode,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Invalid Request: The provided value '" + valuerRejected
                        + " ' doesn't have the expected data type for the property " + propertyName + ".",
                argumentException.getMessage(),
                timestamps,
                null
        );
        return ResponseEntity.status(httpStatusCode).body(apiError);
    }
}
