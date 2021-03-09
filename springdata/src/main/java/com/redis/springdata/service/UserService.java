package com.redis.springdata.service;

import com.redis.springdata.model.User;

import java.util.List;

public interface UserService {
    boolean saveUser(User user);

    List<User> fetchAllUser();

    User fetchById(Long id);
}
