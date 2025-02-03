package com.application.jetbill.movie_management.service.impl;

import com.application.jetbill.movie_management.entity.Rating;
import com.application.jetbill.movie_management.exception.ObjectNotFoundException;
import com.application.jetbill.movie_management.repository.RatingCrudRepository;
import com.application.jetbill.movie_management.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class RatingServiceImpl implements RatingService {
    private final RatingCrudRepository ratingCrudRepository;

    public RatingServiceImpl(RatingCrudRepository ratingCrudRepository) {
        this.ratingCrudRepository = ratingCrudRepository;
    }

    @Override
    public List<Rating> findAll() {
        return ratingCrudRepository.findAll();
    }

    @Override
    public Rating findOneById(Long id) {
        return ratingCrudRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("[Rating:"+Long.toString(id)+"]"));
    }

    @Override
    public List<Rating> findAllByMovieId(Long movieId) {
        return ratingCrudRepository.findByMovieId(movieId);
    }

    @Override
    public List<Rating> findAllByUsername(String username) {
        return ratingCrudRepository.findByUserUsername(username);
    }

    @Override
    public Rating createOne(Rating rating) {
        return ratingCrudRepository.save(rating);
    }

    @Override
    public Rating updateOneById(Long id, Rating rating) {
        Rating existing = this.findOneById(id);
        existing.setMovieId(rating.getMovieId());
        existing.setUserId(rating.getUserId());
        return ratingCrudRepository.save(existing);
    }

    @Override
    public void deleteOneById(Long id) {

        if(ratingCrudRepository.existsById(id)){
            ratingCrudRepository.deleteById(id);
            return;
        }
            throw new ObjectNotFoundException("[Rating:"+Long.toString(id)+"]");

    }
}
