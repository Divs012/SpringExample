package com.redis.springdata.service;

import com.redis.springdata.model.User;
import com.redis.springdata.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao dao;
    @Override
    public boolean saveUser(User user) {
        return dao.saveUser(user);
    }

    @Override
    public List<User> fetchAllUser() {
        return dao.fetchAllUser();
    }

    @Override
    public User fetchById(Long id) {
        return dao.fetchById(id);
    }
}
