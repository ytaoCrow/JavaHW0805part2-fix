package com.example.applestore.controller;

import com.example.applestore.dto.UserLoginRequest;
import com.example.applestore.dto.UserRegisterRequest;
import com.example.applestore.dto.UserRequest;
import com.example.applestore.model.User;
import com.example.applestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        Integer userId = userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/user/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        User user = userService.login(userLoginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<User> updatePhoneById(@PathVariable Integer userId,
                                                @RequestBody @Valid UserRequest userRequest) {

        //先檢查是否有該號碼？有才會執行下面的程式，沒有則回傳404
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //修改手機號碼
        userService.updateUserPhoneById(userId, userRequest);

        User updateUserPhoneById = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(updateUserPhoneById);
    }
}
