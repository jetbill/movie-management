package com.application.jetbill.movie_management.service.impl;

import com.application.jetbill.movie_management.entity.User;
import com.application.jetbill.movie_management.repository.UserCrudRepository;
import com.application.jetbill.movie_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserCrudRepository userCrudRepository;

    public UserServiceImpl(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    @Override
    public List<User> findAll() {
        return userCrudRepository.findAll();
    }

    @Override
    public List<User> findAlByName(String name) {
        return userCrudRepository.findByName(name);
    }

    @Override
    public User findOneByUserName(String username) {
        return userCrudRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("[User:"+username+"] not found"));
    }

    @Override
    public User saveOne(User user) {
        return userCrudRepository.save(user);
    }

    @Override
    public User updateOneByUsername(String username, User user) {
        User existing = findOneByUserName(username);
        existing.setName(user.getName());
        existing.setPassword(user.getPassword());
        return userCrudRepository.save(existing);
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
            throw new RuntimeException("[User:"+username+"] not found");
        }

    }
}
