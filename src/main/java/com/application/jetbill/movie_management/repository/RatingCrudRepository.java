package com.application.jetbill.movie_management.repository;

import com.application.jetbill.movie_management.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingCrudRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByMovieId(Long movieId);
    List<Rating> findByUserUsername(String username);


    
}
