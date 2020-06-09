package com.spring.mongodb.controller;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.spring.mongodb.dao.UserDao;
import com.spring.mongodb.pojo.User;
import com.spring.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/7 12:13
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @RequestMapping("/page")
    public String page() {
        return "user";
    }
    @RequestMapping("/save")
    @ResponseBody
    public User saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    //localhost:8080/user/get?id=1
    @RequestMapping("/get")
    @ResponseBody
    public User getUser(Long id){
        User user = userService.getUser(id);
        return user;
    }

    @RequestMapping("/find")
    @ResponseBody
    public List<User> addUser(User user,String userName, String note, Integer skip, Integer limit) {
        List<User> userList = userService.findUser(userName, note, skip, limit);
        return userList;
    }

    @RequestMapping("/update")
    @ResponseBody
    public UpdateResult updateUser(User user,Long id, String userName, String note) {
        return userService.updateUser(id, userName, note);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public DeleteResult deleteUser(Long id) {
        return userService.deleteUser(id);
    }

    @RequestMapping("/byName")
    @ResponseBody
    public List<User> findByUserName(String userName) {
        return userDao.findByUserNameLike(userName);
    }

    //展示用户详情
    @RequestMapping("/details")
    public ModelAndView details(Long id){
        User user = userService.getUser(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/details");
        mv.addObject("user", user);
        return mv;
    }

    @RequestMapping("/detailForJson")
    public ModelAndView detailsForJson(Long id) {
        User user = userService.getUser(id);
        ModelAndView mv = new ModelAndView();
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        mv.setView(jsonView);
        mv.addObject("user", user);
        return mv;
    }

}
