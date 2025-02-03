package com.application.jetbill.movie_management.service;

import com.application.jetbill.movie_management.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    List<User> findAlByName(String name);
    User findOneByUserName(String username);
    User saveOne(User user);
    User updateOneByUsername(String username, User user);
    void deleteOneByUsername(String username);
}
