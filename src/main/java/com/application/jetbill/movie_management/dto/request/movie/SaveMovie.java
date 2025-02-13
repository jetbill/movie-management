package com.application.jetbill.movie_management.dto.request.movie;

import com.application.jetbill.movie_management.entity.enums.MovieGenre;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record SaveMovie(
        @Size(min = 4, max = 255, message = "{generic.size}")
        @NotBlank(message = "{generic.notblank}")
        String title,
        @Size(min = 4, max = 255, message = "{generic.size}")
        @NotBlank(message = "{generic.notblank}")
        String director,
        MovieGenre genre,
        @Min(value = 1900, message = "{generic.min}")
        @JsonProperty(value = "release_year")
        int releaseYear
) implements Serializable {
}
