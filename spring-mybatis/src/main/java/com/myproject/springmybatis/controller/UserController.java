package com.myproject.springmybatis.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/11 9:49
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequiresPermissions({"user:show"})
    @GetMapping("/show")
    @ResponseBody
    public String showUser(){
        return "展示用户信息";
    }
}
