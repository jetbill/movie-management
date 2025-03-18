package com.application.jetbill.movie_management.repository;

import com.application.jetbill.movie_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;


import java.util.List;
import java.util.Optional;

public interface UserCrudRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    List<User> findByName(String name);

    Optional<User> findByUsername(String username);

    @Modifying
    int deleteByUsername(String username);
}
