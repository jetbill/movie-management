package com.application.jetbill.movie_management.repository.specification;

import com.application.jetbill.movie_management.dto.request.user.UserCriteriaSearch;
import com.application.jetbill.movie_management.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FindUserSpecification implements Specification<User> {

    private final UserCriteriaSearch userCriteriaSearch;
    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.hasText(userCriteriaSearch.name())){
            Predicate titleLike = criteriaBuilder.like(root.get("name"), "%"+userCriteriaSearch.name()+"%");
            predicates.add(titleLike);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
