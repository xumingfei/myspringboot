package com.spring.redis.springredis.controller;

import com.spring.redis.springredis.pojo.User;
import com.spring.redis.springredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/5 10:11
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getUser/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @RequestMapping("/insertUser")
    public User insertUser(String userName, String note) {
        User user = new User();
        user.setUserName(userName);
        user.setNote(note);
        userService.insertUser(user);
        return user;
    }

    @RequestMapping("/findUsers")
    public List<User> findUsers(String userName, String note) {
        return userService.findUsers(userName, note);
    }

    @RequestMapping("/updateUserName")
    public Map<String, Object> updateUserName(Long id, String userName) {
        User user = userService.getUser(id);
        boolean flag = user != null;
        String msg = flag? "更新成功" : "更新失败";
        return resultMap(flag, msg);
    }

    public Map<String, Object> deleteUser(Long id) {
        int result = userService.deleteUser(id);
        boolean flag = result ==1;
        String msg = flag?"删除失败":"删除成功";
        return resultMap(flag, msg);
    }

    private Map<String, Object> resultMap(boolean success, String msg) {
        Map<String,Object> result = new HashMap<>();
        result.put("success", success);
        result.put("msg", msg);
        return result;
    }
}
