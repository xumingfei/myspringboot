package com.myproject.springmybatis.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

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

    public void test(HttpServletRequest req) {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
        webApplicationContext.getBean("");
    }

}
