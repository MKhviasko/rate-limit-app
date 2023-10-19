package com.ratelimit.app.services;

import com.ratelimit.app.data.UserEntity;

public interface UserService {
    UserEntity findUserByUsername(final String username);
}
