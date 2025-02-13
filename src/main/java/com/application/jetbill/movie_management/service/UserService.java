package com.application.jetbill.movie_management.service;

import com.application.jetbill.movie_management.dto.request.user.SaveUser;
import com.application.jetbill.movie_management.dto.response.user.GetUser;


import java.util.List;

public interface UserService {

    List<GetUser> findAll();
    List<GetUser> findAllByName(String name);
    GetUser findOneByUserName(String username);
    GetUser saveOne(SaveUser userDto);
    GetUser updateOneByUsername(String username, SaveUser userDto);
    void deleteOneByUsername(String username);
}
