package com.application.jetbill.movie_management.controllers;

import com.application.jetbill.movie_management.entity.User;
import com.application.jetbill.movie_management.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{username}")
    public User findOneByUsername(@PathVariable String username) {
        return userService.findOneByUserName(username);
    }
}
