package com.example.rentacar.service.impl;

import com.example.rentacar.convertor.UserConvertor;
import com.example.rentacar.dto.UserRequest;
import com.example.rentacar.dto.UserResponse;
import com.example.rentacar.entity.User;
import com.example.rentacar.repository.UserRepository;
import com.example.rentacar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private final UserConvertor userConvertor;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserConvertor userConvertor, BCryptPasswordEncoder bCryptPasswordEncoder,
                           UserRepository userRepository) {
        this.userConvertor = userConvertor;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse registerUser(UserRequest userRequest) {

        User userToBeSaved = userConvertor.toUserRequest(userRequest);
        userToBeSaved.setPassword(bCryptPasswordEncoder.encode(userToBeSaved.getPassword()));
        return userConvertor.toUserResponse(userRepository.save(userToBeSaved));
    }

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User deleteUser(Long id) {
       return userRepository.deleteById(id);
    }


}
