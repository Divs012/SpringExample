package com.redis.springdata.dao;

import com.redis.springdata.model.User;

import java.util.List;

public interface UserDao {
    boolean saveUser(User user);

    List<User> fetchAllUser();

    User fetchById(Long id);
}
