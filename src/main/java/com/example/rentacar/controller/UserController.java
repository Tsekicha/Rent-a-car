package com.example.rentacar.controller;

import com.example.rentacar.dto.UserRequest;
import com.example.rentacar.dto.UserResponse;
import com.example.rentacar.entity.User;
import com.example.rentacar.repository.UserRepository;
import com.example.rentacar.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @PostMapping(path = "/register")
    ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.registerUser(userRequest));
    }
    @GetMapping(path = "/{id}")
    ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        return  ResponseEntity
                .status(HttpStatus.FOUND)
                .body(userRepository.findById(id));
    }
    @GetMapping(path = "/email")
    User getUserByEmail(@RequestParam("email") String email){

        return userRepository.findByEmail(email);
    }
    @DeleteMapping(path = "/delete/{id}")
    @Transactional
    ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userRepository.deleteUserById(id);
        return ResponseEntity
                .ok()
                .body(String.format("%d deleted", id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(user,id);
        return ResponseEntity.ok(updatedUser);
    }

}
