package com.application.jetbill.movie_management.exception.strategy.impl;

import com.application.jetbill.movie_management.dto.response.ApiError;
import com.application.jetbill.movie_management.exception.strategy.ExceptionHandlerStrategy;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;

import java.time.LocalDateTime;

public class HttpMediaTypeNotSupportedExceptionHandler implements ExceptionHandlerStrategy {
    @Override
    public ResponseEntity<ApiError> handleException(Exception exception,
                                                    HttpServletRequest request,
                                                    LocalDateTime timestamps) {
        HttpMediaTypeNotSupportedException mediaType = (HttpMediaTypeNotSupportedException) exception;

        int statusCode = HttpStatus.UNSUPPORTED_MEDIA_TYPE.value();
        ApiError apiError = new ApiError(
                statusCode,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Unsupported Media Type: The server is unable to process the requested entity in the format provided" +
                        " in the request. Supported media types are: " + mediaType.getSupportedMediaTypes()+
                " and you send: "+mediaType.getContentType(),
                exception.getMessage(),
                timestamps,
                null
        );
        return ResponseEntity.status(statusCode).body(apiError);
    }
}
