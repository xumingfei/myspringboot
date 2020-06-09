package com.spring.mongodb.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.spring.mongodb.pojo.User;

import java.util.List;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/7 12:14
 */
public interface UserService {
    void saveUser(User user);

    DeleteResult deleteUser(Long id);

    List<User> findUser(String userName, String notw, int skip, int limit);

    UpdateResult updateUser(Long id, String userName, String note);

    User getUser(Long id);

}
