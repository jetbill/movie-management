package com.application.jetbill.movie_management.dto.request.movie;

import com.application.jetbill.movie_management.entity.enums.MovieGenre;

import java.io.Serializable;

public record MovieSearchCriteria(
        String title,
        MovieGenre genre,
        Integer minReleaseYear
)  implements Serializable {
}
