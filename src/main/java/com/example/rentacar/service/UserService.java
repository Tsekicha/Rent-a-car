package com.example.rentacar.service;

import com.example.rentacar.dto.UserRequest;
import com.example.rentacar.dto.UserResponse;
import com.example.rentacar.entity.User;


public interface UserService {

    UserResponse registerUser(UserRequest userRequest);
    //void updatePassword(UserPasswordUpdate user) throws RecordNotFoundException;
    User findByEmail(String email);
    User findById(Long id);
    User deleteUser(Long id);
}
