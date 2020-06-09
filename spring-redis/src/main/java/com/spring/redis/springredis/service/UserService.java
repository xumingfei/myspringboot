package com.spring.redis.springredis.service;

import com.spring.redis.springredis.pojo.User;

import java.util.List;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/5 9:59
 */
public interface UserService {
    User getUser(Long id);

    User insertUser(User user);

    User updateUserName(Long id, String userName);

    List<User> findUsers(String userName, String note);

    int deleteUser(Long id);
}
