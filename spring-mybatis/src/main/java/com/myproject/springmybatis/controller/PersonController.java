package com.myproject.springmybatis.controller;

import com.myproject.springmybatis.model.Person;
import com.myproject.springmybatis.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PersonController {

    Logger logger = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    PersonService service;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("index");
        List<Person> list = service.findAll();
        mav.addObject("list",list);
        return mav;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(){
        Person person = new Person();
        person.setMobile("123");
        person.setUserName("zhangsan");
        person.setPassword("123");
        service.addPerson(person);
        logger.info("新增人员");
        return person.toString();
    }


}
