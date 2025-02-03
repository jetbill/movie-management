package com.application.jetbill.movie_management.service;

import com.application.jetbill.movie_management.entity.Rating;

import java.util.List;

public interface RatingService {
    List<Rating> findAll();
    Rating findOneById(Long id);
    List<Rating> findAllByMovieId(Long movieId);
    List<Rating> findAllByUsername(String username);
    Rating createOne(Rating rating);
    Rating updateOneById(Long id, Rating rating);
    void deleteOneById(Long id);
}
