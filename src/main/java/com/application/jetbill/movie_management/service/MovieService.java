package com.application.jetbill.movie_management.service;

import com.application.jetbill.movie_management.entity.Movie;
import com.application.jetbill.movie_management.entity.enums.MovieGenre;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    List<Movie> findAllByTitle(String title);
    List<Movie> findAllByGenre(MovieGenre movieGenre);
    List<Movie> findAllByGenreAndTitle(MovieGenre movieGenre, String title);
    Movie createOne(Movie movie);
    Movie updateOneById(Long id, Movie movie);
    void deleteOneById(Long id);
    Movie findOneById(Long id);
}
