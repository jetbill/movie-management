package com.application.jetbill.movie_management.mappers;

import com.application.jetbill.movie_management.dto.response.movie.GetMovie;
import com.application.jetbill.movie_management.dto.response.user.GetUser;
import com.application.jetbill.movie_management.entity.Rating;

import java.util.List;

public class RatingMapper {

    public static GetMovie.GetRating toGetMovieRatingDto(Rating entity) {
        if (entity == null) return null;
        String username = entity.getUser() != null
                ? entity.getUser().getUsername() : null;
        return new GetMovie.GetRating(
                entity.getId(),
                entity.getRating(),
                username);
    }

    public static List<GetMovie.GetRating> toGetMovieRatingsDto(List<Rating> entities) {
        if(entities == null) return null;
        return entities.stream()
                .map(RatingMapper::toGetMovieRatingDto)
                .toList();
    }

    public static GetUser.GetRating toGetUserRatingDto(Rating entity) {
        if (entity == null) return null;
        String movieTitle = entity.getMovie() !=null
                ? entity.getMovie().getTitle() : null;
        return new GetUser.GetRating(
                entity.getId(),
                movieTitle,
                entity.getMovieId(),
                entity.getRating()
        );

    }

    public static List<GetUser.GetRating> toGetUserRatingsDto(List<Rating> entities) {
        if(entities == null) return null;
        return entities.stream()
                .map(RatingMapper::toGetUserRatingDto)
                .toList();

    }

}
