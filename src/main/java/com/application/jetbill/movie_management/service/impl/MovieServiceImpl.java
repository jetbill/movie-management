package com.application.jetbill.movie_management.service.impl;

import com.application.jetbill.movie_management.entity.Movie;
import com.application.jetbill.movie_management.entity.enums.MovieGenre;
import com.application.jetbill.movie_management.exception.ObjectNotFoundException;
import com.application.jetbill.movie_management.repository.MovieCrudRepository;
import com.application.jetbill.movie_management.service.MovieService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {
    private final MovieCrudRepository movieCrudRepository;

    public MovieServiceImpl(MovieCrudRepository movieCrudRepository) {
        this.movieCrudRepository = movieCrudRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movie> findAll() {
        return movieCrudRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAllByTitle(String title) {
        return movieCrudRepository.findByTitleContaining(title);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAllByGenre(MovieGenre movieGenre) {
        return movieCrudRepository.findByGenre(movieGenre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAllByGenreAndTitle(MovieGenre movieGenre, String title) {
        return movieCrudRepository.findByGenreAndTitleContaining(movieGenre, title);
    }

    @Override
    public Movie createOne(Movie movie) {
        return movieCrudRepository.save(movie);
    }

    @Override
    public Movie updateOneById(Long id, Movie newMovie) {
        Movie oldMovie = this.findOneById(id);
        oldMovie.setTitle(newMovie.getTitle());
        oldMovie.setGenre(newMovie.getGenre());
        oldMovie.setDirector(newMovie.getDirector());
        oldMovie.setReleaseYear(newMovie.getReleaseYear());
        return movieCrudRepository.save(oldMovie);
    }

    @Override
    public void deleteOneById(Long id) {
        Movie movie = this.findOneById(id);
        movieCrudRepository.delete(movie);

    }

    @Override
    public Movie findOneById(Long id) {
        return movieCrudRepository.findById(id)
               .orElseThrow(() -> new ObjectNotFoundException("[Movie:"+Long.toString(id)+"]"));
    }
}
