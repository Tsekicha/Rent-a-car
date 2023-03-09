package com.example.rentacar.controller;

import com.example.rentacar.dto.UserRequest;
import com.example.rentacar.dto.UserResponse;
import com.example.rentacar.entity.User;
import com.example.rentacar.repository.UserRepository;
import com.example.rentacar.service.UserService;
import com.example.rentacar.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping(path = "/register")
    ResponseEntity<UserResponse> save(@RequestBody UserRequest userRequest){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.registerUser(userRequest));
    }


}
