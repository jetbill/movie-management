package com.application.jetbill.movie_management.mappers;

import com.application.jetbill.movie_management.dto.request.movie.SaveMovie;
import com.application.jetbill.movie_management.dto.response.movie.GetMovie;
import com.application.jetbill.movie_management.entity.Movie;

import java.util.List;

public class MovieMapper {
    public static GetMovie toGetDto(Movie entity) {
        if(entity == null) return null;
        return new GetMovie(
                entity.getId(),
                entity.getTitle(),
                entity.getDirector(),
                entity.getGenre(),
                entity.getReleaseYear(),
                RatingMapper.toGetMovieRatingsDto(entity.getRatings())
        );



    }

    public static List<GetMovie> toGetDtoList(List<Movie> entities){
        if(entities == null) return null;
        return entities
                .stream()
                .map(MovieMapper::toGetDto)
                .toList();

    }

    public static Movie toEntity(SaveMovie saveDto) {
        if(saveDto == null) return null;
        Movie entity = new Movie();
        entity.setTitle(saveDto.title());
        entity.setDirector(saveDto.director());
        entity.setGenre(saveDto.genre());
        entity.setReleaseYear(saveDto.releaseYear());
        return entity;
    }

    public static void updateEntity(Movie oldEntity, SaveMovie saveDto) {
        if (oldEntity == null || saveDto == null) return;
        oldEntity.setTitle(saveDto.title());
        oldEntity.setDirector(saveDto.director());
        oldEntity.setGenre(saveDto.genre());
        oldEntity.setReleaseYear(saveDto.releaseYear());

    }
}
