package com.myproject.springmybatis.service;

import com.myproject.springmybatis.model.User;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/11 9:47
 */
public interface UserService {
    int save(User user);

    int del(Long id);

    int update(User user);

    String getPassword(String userName);

    User getUserByLoginName(String userName);
}
