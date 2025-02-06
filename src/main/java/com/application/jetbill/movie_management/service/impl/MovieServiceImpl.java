package com.application.jetbill.movie_management.service.impl;

import com.application.jetbill.movie_management.dto.request.SaveMovie;
import com.application.jetbill.movie_management.dto.response.GetMovie;
import com.application.jetbill.movie_management.entity.Movie;
import com.application.jetbill.movie_management.entity.enums.MovieGenre;
import com.application.jetbill.movie_management.exception.ObjectNotFoundException;
import com.application.jetbill.movie_management.mappers.MovieMapper;
import com.application.jetbill.movie_management.repository.MovieCrudRepository;
import com.application.jetbill.movie_management.service.MovieService;

import org.springframework.data.jpa.repository.Modifying;
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
    public List<GetMovie> findAll() {
        return  MovieMapper.toGetDtoList(movieCrudRepository.findAll());

    }

    @Override
    @Transactional(readOnly = true)
    public List<GetMovie> findAllByTitle(String title) {

        return MovieMapper.toGetDtoList(movieCrudRepository.findByTitleContaining(title));
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetMovie> findAllByGenre(MovieGenre movieGenre) {
        return MovieMapper.toGetDtoList(movieCrudRepository.findByGenre(movieGenre));
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetMovie> findAllByGenreAndTitle(MovieGenre movieGenre, String title) {
        return MovieMapper.toGetDtoList(movieCrudRepository.findByGenreAndTitleContaining(movieGenre, title));
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
