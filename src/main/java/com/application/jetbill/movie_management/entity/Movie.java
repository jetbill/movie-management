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

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String director;

    @Enumerated(EnumType.STRING)
    private MovieGenre genre;

    @JsonProperty(value = "release-year")
    private int releaseYear;



    @CreationTimestamp
    @JsonFormat(pattern = "yyyy/MM/dd - HH:mm:ss")
    @JsonProperty(value = "create-at")
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime createAt;

    public LocalDateTime getCreateAt() {
        return createAt;
    }
    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "movie",cascade = CascadeType.ALL)
    @JsonManagedReference("movie-to-rating")
    private List<Rating> ratings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
