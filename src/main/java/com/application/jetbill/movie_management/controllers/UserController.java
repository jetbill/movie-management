package com.application.jetbill.movie_management.controllers;

import com.application.jetbill.movie_management.dto.request.SaveUser;
import com.application.jetbill.movie_management.dto.response.GetUser;
import com.application.jetbill.movie_management.exception.ObjectNotFoundException;
import com.application.jetbill.movie_management.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<GetUser>> findAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }
    @GetMapping(value = "/{username}")
    public ResponseEntity<GetUser> findOneByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok(userService.findOneByUserName(username));
        }catch (ObjectNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<GetUser> createUser(@RequestBody SaveUser saveUserDto,
                                              HttpServletRequest request) {
        GetUser userCreated = userService.saveOne(saveUserDto);
        String baseUrl = request.getRequestURL().toString();
        URI newLocation = URI.create(baseUrl + "/" + userCreated.name());
        return ResponseEntity.created(newLocation).body(userCreated);

    }

    @PutMapping("/{username}")
    public ResponseEntity<GetUser> updateOneByUsername(@PathVariable String username,
                                                       @RequestBody SaveUser saveUserDto) {
        try{
            GetUser userUpdated = userService.updateOneByUsername(username, saveUserDto);
            return ResponseEntity.ok(userUpdated);
        }catch (ObjectNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Void> deleteOneByUserName(@PathVariable String username){
        try {
            userService.deleteOneByUsername(username);
            return ResponseEntity.noContent().build();
        }catch (ObjectNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
