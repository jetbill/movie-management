package com.application.jetbill.movie_management.service.impl;

import com.application.jetbill.movie_management.dto.request.movie.MovieSearchCriteria;
import com.application.jetbill.movie_management.dto.request.movie.SaveMovie;
import com.application.jetbill.movie_management.dto.response.movie.GetMovie;
import com.application.jetbill.movie_management.entity.Movie;
import com.application.jetbill.movie_management.entity.enums.MovieGenre;
import com.application.jetbill.movie_management.exception.ObjectNotFoundException;
import com.application.jetbill.movie_management.mappers.MovieMapper;
import com.application.jetbill.movie_management.repository.MovieCrudRepository;
import com.application.jetbill.movie_management.repository.specification.FindAllMoviesSpecification;
import com.application.jetbill.movie_management.service.MovieService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<GetMovie> findAll(MovieSearchCriteria movieSearchCriteria, Pageable pageable) {
        FindAllMoviesSpecification moviesSpecification = new FindAllMoviesSpecification(movieSearchCriteria);
        Page<Movie> entities = movieCrudRepository.findAll(moviesSpecification, pageable);
        return entities.map(MovieMapper::toGetDto);


    }



    @Override
    public GetMovie createOne(SaveMovie saveDto) {
        Movie movieEntity = MovieMapper.toEntity(saveDto);
        return MovieMapper.toGetDto(movieCrudRepository.save(movieEntity));
    }

    @Override
    public GetMovie updateOneById(Long id, SaveMovie movieDto) {
        Movie existingMovie = this.findOneEntityById(id);
        MovieMapper.updateEntity(existingMovie, movieDto);
        return MovieMapper.toGetDto(movieCrudRepository.save(existingMovie));

    }

    @Override
    public void deleteOneById(Long id) {
        Movie movie = this.findOneEntityById(id);
        movieCrudRepository.delete(movie);

    }

    @Override
    public GetMovie findOneById(Long id) {
        return MovieMapper.toGetDto(this.findOneEntityById(id));
    }

    @Transactional(readOnly = true)
    public Movie findOneEntityById(Long id) {
    return movieCrudRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("[Movie:"+Long.toString(id)+"]"));
    }
}
