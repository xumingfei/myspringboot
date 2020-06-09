package com.myproject.springmybatis.controller;

import com.myproject.springmybatis.model.Person;
import com.myproject.springmybatis.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class PersonController {

    Logger logger = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    PersonService personservice;

    @RequestMapping("/index")
    public ModelAndView index(HttpSession session){
        ModelAndView mav = new ModelAndView("index");
        if (StringUtils.isEmpty((String) session.getAttribute("userName"))) {
            mav.setViewName("login");
        }
        List<Person> list = personservice.findAll();
        mav.addObject("list",list);
        return mav;
    }

    @RequestMapping("/add")
//    @ResponseBody
    public String add(Map map){
        Person person = new Person();
//        person.setMobile("123");
//        person.setUserName("zhangsan");
//        person.setPassword("123");
//        personservice.addPerson(person);
//        logger.info("新增人员");
        map.put("person", person);
        return "person/personForm";
    }

    @RequestMapping("/save")
    public String save(Person person, Map map){
        personservice.addPerson(person);
        map.put("list", personservice.findAll());
        return "redirect:index";
    }

    @RequestMapping("/edit")
    public String getPerson(Long id, Model model){
        Person person = personservice.getPersonById(id);
        model.addAttribute("person", person);
        return "person/personForm";
    }

    @RequestMapping("/delete")
    public String delete(Long id,Map map) {
        personservice.deleteById(id);
        map.put("list", personservice.findAll());
        return "redirect:index";
    }

    @RequestMapping("/register")
    public String register(){
        return "person/register";
    }

    @RequestMapping("/success")
    public String success(Model model,Person person){

        personservice.addPerson(person);
        model.addAttribute("msg", "注册成功");
        return "success";
    }

    @RequestMapping("/form")
    public String form(){
        return "form";
    }


}
