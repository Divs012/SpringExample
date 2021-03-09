package com.redis.springdata.controller;

import com.redis.springdata.model.User;
import com.redis.springdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
@Autowired

    private UserService service;
@PostMapping("/user")
public ResponseEntity<String> saveUser(@RequestBody User user){
    boolean result=service.saveUser(user);
    if(result)
        return ResponseEntity.ok("User Created");
    else
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

}
@GetMapping("/user/{id}")
public ResponseEntity <User> UserById(@PathVariable Long id){
    User user=service.fetchById(id);

    return new ResponseEntity<User>(user,HttpStatus.OK);
    }
@GetMapping("/user")
public ResponseEntity <List<User>> fetchAllUser(){
    List<User>users=service.fetchAllUser();

    return ResponseEntity.ok(users);
}
}

