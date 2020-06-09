package com.practice.springbootmybatis.service.impl;

import com.practice.springbootmybatis.entity.User;
import com.practice.springbootmybatis.mapper.UserMapper;
import com.practice.springbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }
}
