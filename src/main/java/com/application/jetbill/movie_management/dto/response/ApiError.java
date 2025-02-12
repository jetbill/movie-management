package com.application.jetbill.movie_management.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        @JsonProperty(value = "http_code")
        int httpCode,
        String url,
        @JsonProperty(value = "http_method")
        String httpMethod,
        String message,
        @JsonProperty(value = "backend_message")
        String backendMessage,
        LocalDateTime timestamp,
        List<String> details
) {
}
