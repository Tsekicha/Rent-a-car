package com.example.rentacar.service;

import com.example.rentacar.dto.UserRequest;
import com.example.rentacar.dto.UserResponse;
import com.example.rentacar.entity.User;


public interface UserService {

    UserResponse registerUser(UserRequest userRequest);
    User updateUser(User user, Long id);
    User findByEmail(String email);
    User findById(Long id);
    void deleteUser(Long id);
}
