package com.example.applestore.service.impl;

import com.example.applestore.dao.UserDao;
import com.example.applestore.dto.UserLoginRequest;
import com.example.applestore.dto.UserRegisterRequest;
import com.example.applestore.dto.UserRequest;
import com.example.applestore.model.User;
import com.example.applestore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        //事件處理-檢查Email
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        log.warn("該 email {} 已經被註冊", userRegisterRequest.getEmail());
        if(user != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //MD5加密密碼
        //更進階可以使用 Salt 避免被 Rainbow Table 破解，有空我再去研究
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);

        //創建帳號
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        //事件處理-check user
        if(user == null){
            log.warn("該 email {} 尚未註冊", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //使用MD5生成的密碼
        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

        //事件處理-password
        if(user.getPassword().equals(hashedPassword)){
            return user;
        }else {
            log.warn("email {} 的密碼不正確", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void updateUserPhoneById(Integer userId, UserRequest userRequest) {
        userDao.updateUserPhoneById(userId, userRequest);
    }
}
