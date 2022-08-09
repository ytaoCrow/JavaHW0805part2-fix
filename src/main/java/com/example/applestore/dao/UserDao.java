package com.example.applestore.dao;

import com.example.applestore.dto.UserRegisterRequest;
import com.example.applestore.dto.UserRequest;
import com.example.applestore.model.User;

public interface UserDao {
    User getUserById(Integer userId);

    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);

    void updateUserPhoneById(Integer userId, UserRequest userRequest);
}
