package com.redis.springdata.dao;

import com.redis.springdata.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class UserDaoImpl implements UserDao{
    @Autowired
    private RedisTemplate template;
    private static final String KEY="USER";
    @Override
    public boolean saveUser(User user) {
        try{
            template.opsForHash().put(KEY, user.getId().toString(), user);
            return true;        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> fetchAllUser() {
        List<User> users;
        users=template.opsForHash().values(KEY);
        return users;
    }

    @Override
    public User fetchById(Long id) {
        User user;
        user= (User)template.opsForHash().get(KEY, id);
        return user;
    }
}
