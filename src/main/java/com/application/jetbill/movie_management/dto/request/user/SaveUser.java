package com.application.jetbill.movie_management.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record SaveUser(
        @Size(min = 3, max = 50 , message = "{generic.size}")
        String name,
        @Pattern(regexp = "[a-zA-Z0-9-_]{8,255}", message = "{saveUser.username.pattern}")
        String username,
        @Size(min = 10, max = 255, message = "{generic.size}")
        @NotBlank(message = "{generic.notblank}")
        String password,
        @JsonProperty(value = "password_repeated")
        @NotBlank(message = "{generic.notblank}")
        @Size(min = 10, max = 255)
        String passwordRepeated
) implements Serializable {
}
