package com.xiang.example.provider;

import com.xiang.example.common.model.User;
import com.xiang.example.common.service.UserService;

/**
 * @Author kekai-wurongwei
 * @Date 2024 07 24 20 25
 **/
public class UserServiceImpl implements UserService {


    @Override
    public User getUser(User user) {
        System.out.println("用户名： "+ user.getName());
        return user;
    }
}
