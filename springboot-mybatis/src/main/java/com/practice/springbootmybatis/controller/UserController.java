package com.practice.springbootmybatis.controller;

import com.practice.springbootmybatis.entity.User;
import com.practice.springbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/{id}")
    public String getUser(@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        return user.toString();
    }

    @PostMapping("/getUser")
    public User getUserByPost(@RequestParam("id") String id){
        User user = userService.getUserById(Long.valueOf(id));
        return user;
    }

    public String addUser(){
        return "success";
    }
}
