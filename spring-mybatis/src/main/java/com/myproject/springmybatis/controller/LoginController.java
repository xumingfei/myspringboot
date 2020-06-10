package com.myproject.springmybatis.controller;

import com.myproject.springmybatis.model.Person;
import com.myproject.springmybatis.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/8 14:11
 */
@Controller
public class LoginController {

    @Autowired
    PersonService personService;

    @RequestMapping("/login")
    public String gologin(){
        return "login";
    }


    @PostMapping("/login")
    public String login(String userName, String password, ModelMap map, HttpSession session) {
        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
            session.setAttribute("userName", userName);
            Person person = personService.isValid(userName, password);
            System.out.println(person);
            System.out.println("---"+userName);
            if (person != null) {
                map.put("age", 30);
                map.addAttribute("user",person);
                map.put("list", personService.findAll());
                return "redirect:/index";
            }else {
                session.invalidate();
                map.put("msg", "用户名密码错误");
                return "login";
            }
        }else {
            session.invalidate();
            map.put("msg", "用户名密码错误");
            return "login";
        }
    }

    @GetMapping("/index1")
    public String index1(Map map){
        Person person = personService.isValid("张三", "123456");
        map.put("thObject", person);
        return "index1";
    }



    @RequestMapping("/hellow")
    @ResponseBody
    public String hello(){
        return "hello";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        Object userName = session.getAttribute("userName");
        if (userName != null) {
            session.removeAttribute("userName");
        }
        return "forward:/login";
    }
}
