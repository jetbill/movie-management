package com.application.jetbill.movie_management.service;

import com.application.jetbill.movie_management.dto.request.movie.MovieSearchCriteria;
import com.application.jetbill.movie_management.dto.request.movie.SaveMovie;
import com.application.jetbill.movie_management.dto.response.movie.GetMovie;
import com.application.jetbill.movie_management.entity.enums.MovieGenre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    Page<GetMovie> findAll(MovieSearchCriteria movieSearchCriteria, Pageable pageable);
    //List<GetMovie> findAllByTitle(String title);
    //List<GetMovie> findAllByGenre(MovieGenre movieGenre);
    //List<GetMovie> findAllByGenreAndTitle(MovieGenre movieGenre, String title);
    GetMovie createOne(SaveMovie movieDto);
    GetMovie updateOneById(Long id, SaveMovie movieDto);
    void deleteOneById(Long id);
    GetMovie findOneById(Long id);
}
