package com.myproject.springmybatis.service.impl;

import com.myproject.springmybatis.mapper.UserMapper;
import com.myproject.springmybatis.model.User;
import com.myproject.springmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/11 9:48
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int save(User user) {
        int i = userMapper.save(user);
        return i;
    }

    @Override
    public int del(Long id) {
        return 0;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public String getPassword(String userName) {
        return userMapper.getPassword(userName);
    }

    @Override
    public User getUserByLoginName(String userName) {
        return userMapper.getUserByLoginName(userName);
    }
}
