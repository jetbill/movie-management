package com.application.jetbill.movie_management.entity;

import com.application.jetbill.movie_management.entity.enums.MovieGenre;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String director;
    @Enumerated(EnumType.STRING)
    private MovieGenre genre;
    private int releaseYear;
    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime createAt;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Rating> ratings;


}
