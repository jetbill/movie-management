package com.application.jetbill.movie_management.controllers;


import com.application.jetbill.movie_management.entity.Movie;
import com.application.jetbill.movie_management.entity.enums.MovieGenre;
import com.application.jetbill.movie_management.exception.ObjectNotFoundException;
import com.application.jetbill.movie_management.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Movie>> findAll(@RequestParam (required = false) String title,
                                              @RequestParam(required = false) MovieGenre genre) {
        List<Movie> movies = null;
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
     public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(movieService.findOneById(id));
           // return ResponseEntity.ok(movieService.findOneById(id));
        } catch (ObjectNotFoundException e) {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            return ResponseEntity.notFound().build();
        }

    }
}
