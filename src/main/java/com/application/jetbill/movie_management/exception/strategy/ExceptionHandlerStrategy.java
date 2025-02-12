package com.application.jetbill.movie_management.exception.strategy;

import com.application.jetbill.movie_management.dto.response.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface ExceptionHandlerStrategy {
    ResponseEntity<ApiError> handleException(Exception exception,
                                             HttpServletRequest request,
                                             LocalDateTime timestamps);
}
