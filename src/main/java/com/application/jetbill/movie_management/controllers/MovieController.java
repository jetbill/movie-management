package com.application.jetbill.movie_management.controllers;


import com.application.jetbill.movie_management.dto.request.SaveMovie;
import com.application.jetbill.movie_management.dto.request.SaveUser;
import com.application.jetbill.movie_management.dto.response.GetMovie;
import com.application.jetbill.movie_management.entity.Movie;
import com.application.jetbill.movie_management.entity.enums.MovieGenre;
import com.application.jetbill.movie_management.exception.ObjectNotFoundException;
import com.application.jetbill.movie_management.service.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<GetMovie>> findAll(@RequestParam (required = false) String title,
                                                        @RequestParam(required = false) MovieGenre genre) {
        List<GetMovie> movies = null;
        if(StringUtils.hasText(title) && genre != null) {
            movies = movieService.findAllByGenreAndTitle(genre, title);
        } else if (StringUtils.hasText(title)) {
            movies = movieService.findAllByTitle(title);

        } else if (genre != null) {
            movies = movieService.findAllByGenre(genre);
        }else{
            movies = movieService.findAll();
        }

        return ResponseEntity.status(HttpStatus.OK).body(movies);
        //return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
     public ResponseEntity<GetMovie> findMovieById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(movieService.findOneById(id));
           // return ResponseEntity.ok(movieService.findOneById(id));
        } catch (ObjectNotFoundException e) {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<GetMovie> createOne(@RequestBody SaveMovie movieDto,
                                              HttpServletRequest request) {

        GetMovie movieCreated = movieService.createOne(movieDto);
        String baseUrl = request.getRequestURL().toString();
        URI newLocation = URI.create(baseUrl + "/" + movieCreated.id());
        return ResponseEntity.created(newLocation)
                .body(movieCreated);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GetMovie> updateOneById(@PathVariable Long id, @RequestBody SaveMovie movieDto) {
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
