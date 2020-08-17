package com.myproject.springmybatis.controller;

import com.myproject.springmybatis.model.AjaxResult;
import com.myproject.springmybatis.model.Person;
import com.myproject.springmybatis.service.PersonService;
import com.myproject.springmybatis.service.UserService;
import com.myproject.springmybatis.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/8 14:11
 */
@Controller
public class LoginController extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    PersonService personService;

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String gologin(){
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        log.info("register");
        return "register";
    }

    @PostMapping("/login1")
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

    @PostMapping("/login2")
    @ResponseBody
    public AjaxResult ajaxLogin(String userName, String password, Boolean rememberMe,HttpSession session)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            session.setAttribute("userName",userName);
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                e.printStackTrace();
                msg = e.getMessage();
            }
            return error(msg);
        }
    }
    @PostMapping("/login")
    public String login(String userName, String password, Boolean rememberMe,HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            session.setAttribute("userName",userName);
            return "forward:/index";
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                e.printStackTrace();
                msg = e.getMessage();
            }
            return "login";
        }
    }

}
