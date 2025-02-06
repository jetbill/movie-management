package com.application.jetbill.movie_management.mappers;

import com.application.jetbill.movie_management.dto.request.SaveUser;
import com.application.jetbill.movie_management.dto.response.GetUser;
import com.application.jetbill.movie_management.entity.User;

import java.util.List;

public class UserMapper {

    public static GetUser toGetDto(User user) {
        if (user == null) return null;
        return new GetUser(user.getName(),
                user.getUsername(),
                RatingMapper.toGetUserRatingsDto(user.getRatings()));

    }

    public static User toEntity(SaveUser userDto) {
        if (userDto == null) return null;
        User user = new User();
        user.setName(userDto.name());
        user.setUsername(userDto.username());
        user.setPassword(userDto.password());
        return user;

    }

    public static List<GetUser> toGetDtoList(List<User> users) {
        if (users == null) return null;
        return users.stream()
                .map(UserMapper::toGetDto)
                .toList();
    }

    public static void updateUserToDto(User user, SaveUser saveUserDto) {
        if(user == null || saveUserDto == null) return;
        user.setUsername(saveUserDto.username());
        user.setPassword(saveUserDto.password());
        user.setName(user.getName());

    }

}
