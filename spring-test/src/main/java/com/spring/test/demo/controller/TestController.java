package com.spring.test.demo.controller;

import com.spring.test.demo.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Xiaofei
 * @DATE: 2020/12/31 15:18
 */
@RestController
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @RequestMapping("/hello")
    public String hello(HttpServletRequest request, @RequestParam("username") String username, String name, @RequestBody Person person){
        System.out.println(request.getParameter("name"));
        System.out.println(username+name);
        System.out.println(person.toString());
        logger.info(person.toString());
        logger.debug("",person);
        return "hello";
    }
}
