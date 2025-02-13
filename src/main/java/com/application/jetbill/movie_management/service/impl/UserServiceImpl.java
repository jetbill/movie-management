package com.application.jetbill.movie_management.service.impl;

import com.application.jetbill.movie_management.dto.request.user.SaveUser;
import com.application.jetbill.movie_management.dto.response.user.GetUser;
import com.application.jetbill.movie_management.entity.User;
import com.application.jetbill.movie_management.exception.ObjectNotFoundException;
import com.application.jetbill.movie_management.mappers.UserMapper;
import com.application.jetbill.movie_management.repository.UserCrudRepository;
import com.application.jetbill.movie_management.service.UserService;
import com.application.jetbill.movie_management.service.validator.PasswordValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    private final UserCrudRepository userCrudRepository;

    public UserServiceImpl(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetUser> findAll() {
        return UserMapper.toGetDtoList(userCrudRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetUser> findAllByName(String name) {
        return UserMapper.toGetDtoList(userCrudRepository.findByName(name));
    }

    @Override
    public GetUser findOneByUserName(String username) {
        return UserMapper.toGetDto(this.findOneEntityByUsername(username));
    }

    @Transactional(readOnly = true)
    public User findOneEntityByUsername(String username ) {
        return userCrudRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("[User:"+username+"] not found"));
    }

    @Override
    public GetUser saveOne(SaveUser userDto) {
        PasswordValidator.validatePassword(userDto.password(), userDto.passwordRepeated());
        User newUser = UserMapper.toEntity(userDto);
        return UserMapper.toGetDto(userCrudRepository.save(newUser));
    }

    @Override
    public GetUser updateOneByUsername(String username, SaveUser userDto) {
        PasswordValidator.validatePassword(userDto.password(), userDto.passwordRepeated());
        User existing = this.findOneEntityByUsername(username);
        UserMapper.updateUserToDto(existing, userDto);
        return UserMapper.toGetDto(existing);
    }

    @Override
    public void deleteOneByUsername(String username) {

        /*
        opción : 1
        if(userCrudRepository.findByUsername(username).isPresent()){
            userCrudRepository.deleteByUsername(username);
            return;
        }
        throw new RuntimeException("[User:"+username+"] not found");*/
        // opción : 2
        if(userCrudRepository.deleteByUsername(username)!= 1) {
            throw new ObjectNotFoundException("[User:"+username+"] not found");
        }

    }
}
