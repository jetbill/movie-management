package com.application.jetbill.movie_management.repository.specification;

import com.application.jetbill.movie_management.dto.request.movie.MovieSearchCriteria;
import com.application.jetbill.movie_management.entity.Movie;
import com.application.jetbill.movie_management.entity.enums.MovieGenre;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FindAllMoviesSpecification implements Specification<Movie> {
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
            Predicate releaseYearEqualTo  = criteriaBuilder.equal(root.get("releaseYear"), this.searchCriteria.minReleaseYear());
            predicates.add(releaseYearEqualTo);
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
