package com.application.jetbill.movie_management.controllers;


import com.application.jetbill.movie_management.dto.request.movie.MovieSearchCriteria;
import com.application.jetbill.movie_management.dto.request.movie.SaveMovie;
import com.application.jetbill.movie_management.dto.response.ApiError;
import com.application.jetbill.movie_management.dto.response.movie.GetMovie;
import com.application.jetbill.movie_management.entity.enums.MovieGenre;
import com.application.jetbill.movie_management.exception.ObjectNotFoundException;
import com.application.jetbill.movie_management.service.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<Page<GetMovie>> findAll(@RequestParam (required = false) String title,
                                                  @RequestParam(required = false) MovieGenre genre,
                                                  @RequestParam (required = false, name = "min_release_year") Integer minReleaseYear,
                                                  @RequestParam (required = false, name = "max_release_year") Integer maxReleaseYear,
                                                  @RequestParam (required = false, name = "min_average_rating") Integer minAverageRating,
                                                  @RequestParam (required = false, defaultValue = "0") Integer pageNumber,
                                                  @RequestParam (required = false, defaultValue = "10") Integer pageSize) {
        Pageable moviePageable = PageRequest.of(pageNumber, pageSize);

        MovieSearchCriteria criteria = new MovieSearchCriteria(title, genre, minReleaseYear, maxReleaseYear, minAverageRating);
        Page<GetMovie> movies = movieService.findAll(criteria, moviePageable);

        return ResponseEntity.status(HttpStatus.OK).body(movies);

    }

    @GetMapping(value = "/{id}")
     public ResponseEntity<GetMovie> findMovieById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(movieService.findOneById(id));

        } catch (ObjectNotFoundException e) {

            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<GetMovie> createOne(@RequestBody @Valid SaveMovie movieDto,
                                              HttpServletRequest request) {

        GetMovie movieCreated = movieService.createOne(movieDto);
        String baseUrl = request.getRequestURL().toString();
        URI newLocation = URI.create(baseUrl + "/" + movieCreated.id());
        return ResponseEntity.created(newLocation)
                .body(movieCreated);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GetMovie> updateOneById(@Valid @PathVariable Long id, @RequestBody SaveMovie movieDto) {
        try {
            GetMovie movieUpdated = movieService.updateOneById(id, movieDto);
            return ResponseEntity.ok(movieUpdated);
        }catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOneById(@PathVariable Long id) {
        try{
            movieService.deleteOneById(id);
            return ResponseEntity.noContent().build();
        }catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

}
