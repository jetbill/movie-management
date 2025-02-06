package com.application.jetbill.movie_management.dto.request;

import com.application.jetbill.movie_management.entity.enums.MovieGenre;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record SaveMovie(
        String title,
        String director,
        MovieGenre genre,
        @JsonProperty(value = "release_year")
        int releaseYear
) implements Serializable {
}
