package com.ratelimit.app.services.impl;

import com.ratelimit.app.data.UserEntity;
import com.ratelimit.app.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public UserEntity findUserByUsername(String username) {
        return new UserEntity();
    }
}
