package com.example.applestore.service;

import com.example.applestore.dto.UserLoginRequest;
import com.example.applestore.dto.UserRegisterRequest;
import com.example.applestore.dto.UserRequest;
import com.example.applestore.model.User;

public interface UserService {
    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);

    void updateUserPhoneById(Integer userId, UserRequest userRequest);

    User login(UserLoginRequest userLoginRequest);
}
