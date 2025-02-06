package com.application.jetbill.movie_management.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record SaveUser(
        String name,
        String username,
        String password,
        @JsonProperty(value = "password_repeated")
        String passwordRepeated
) implements Serializable {
}
