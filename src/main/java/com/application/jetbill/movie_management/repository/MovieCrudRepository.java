package com.application.jetbill.movie_management.repository;

import com.application.jetbill.movie_management.entity.Movie;
import com.application.jetbill.movie_management.entity.enums.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MovieCrudRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

   // List<Movie> findByTitleContaining(String title);

    //List<Movie> findByGenre(MovieGenre genre);

   // List<Movie> findByGenreAndTitleContaining(MovieGenre genre, String title);


}
