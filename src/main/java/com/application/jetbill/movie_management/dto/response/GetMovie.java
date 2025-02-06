package com.application.jetbill.movie_management.dto.response;

import com.application.jetbill.movie_management.entity.enums.MovieGenre;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public record GetMovie (
        long id,
        String title,
        String director,
        MovieGenre genre,
        @JsonProperty(value = "release_year")
        int releaseYear,
        List<GetRating> ratings) implements Serializable{

    public static record GetRating (
            long id,
            int rating,
            String username
    )implements Serializable{


    }
}
