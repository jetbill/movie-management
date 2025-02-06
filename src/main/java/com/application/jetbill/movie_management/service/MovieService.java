package com.application.jetbill.movie_management.service;

import com.application.jetbill.movie_management.dto.request.SaveMovie;
import com.application.jetbill.movie_management.dto.response.GetMovie;
import com.application.jetbill.movie_management.entity.Movie;
import com.application.jetbill.movie_management.entity.enums.MovieGenre;

import java.util.List;

public interface MovieService {
    List<GetMovie> findAll();
    List<GetMovie> findAllByTitle(String title);
    List<GetMovie> findAllByGenre(MovieGenre movieGenre);
    List<GetMovie> findAllByGenreAndTitle(MovieGenre movieGenre, String title);
    GetMovie createOne(SaveMovie movieDto);
    GetMovie updateOneById(Long id, SaveMovie movieDto);
    void deleteOneById(Long id);
    GetMovie findOneById(Long id);
}
