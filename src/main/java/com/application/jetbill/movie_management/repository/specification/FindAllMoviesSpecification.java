package com.application.jetbill.movie_management.repository.specification;

import com.application.jetbill.movie_management.dto.request.movie.MovieSearchCriteria;
import com.application.jetbill.movie_management.entity.Movie;
import com.application.jetbill.movie_management.entity.Rating;
import com.application.jetbill.movie_management.entity.enums.MovieGenre;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public final class FindAllMoviesSpecification implements Specification<Movie> {
   private final MovieSearchCriteria searchCriteria;

    public FindAllMoviesSpecification(MovieSearchCriteria searchCriteria) {
       this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(StringUtils.hasText(searchCriteria.title())){
            Predicate titleLike = criteriaBuilder.like(root.get("title"), "%" + this.searchCriteria.title() + "%");
            predicates.add(titleLike);
        }
        if(searchCriteria.genre() != null){
            Predicate genreEqual = criteriaBuilder.equal(root.get("genre"), this.searchCriteria.genre());
            predicates.add(genreEqual);
        }
        if(searchCriteria.minReleaseYear() != null && searchCriteria.minReleaseYear() > 0){
            Predicate releaseYearGreaterThanEqual  = criteriaBuilder.greaterThanOrEqualTo(root.get("releaseYear"),
                    this.searchCriteria.minReleaseYear());
            predicates.add(releaseYearGreaterThanEqual);
        }
        if(searchCriteria.maxReleaseYear() != null && searchCriteria.maxReleaseYear() > 0){
            Predicate releaseYearLessThanEqual  = criteriaBuilder.lessThanOrEqualTo(root.get("releaseYear"),
                    this.searchCriteria.maxReleaseYear());
            predicates.add(releaseYearLessThanEqual);
        }

        if (this.searchCriteria.minAverageRating() != null && this.searchCriteria.minAverageRating() > 0){
            Subquery<Double> minAvergeRatingSubquery = getAveratingRatingSubquery(root, query, criteriaBuilder);
            Predicate averageRatingGreaterThanCriteriaEqual = criteriaBuilder.greaterThanOrEqualTo(minAvergeRatingSubquery,
                    this.searchCriteria.minAverageRating().doubleValue());
            predicates.add(averageRatingGreaterThanCriteriaEqual);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Subquery<Double> getAveratingRatingSubquery(Root<Movie> root, CriteriaQuery<?> query,
                                                        CriteriaBuilder criteriaBuilder) {
         Subquery<Double> averageRatingSubquery = query.subquery(Double.class);
        Root<Rating> ratingRoot = averageRatingSubquery.from(Rating.class);
        averageRatingSubquery.select(criteriaBuilder.avg(ratingRoot.get("rating")));
        Predicate movieIdEqual = criteriaBuilder.equal(root.get("id"), ratingRoot.get("movieId"));
        averageRatingSubquery.where(movieIdEqual);
        return averageRatingSubquery;

    }
}
