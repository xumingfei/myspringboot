package com.myproject.springmybatis.controller;

import com.github.pagehelper.PageInfo;
import com.myproject.springmybatis.model.Person;
import com.myproject.springmybatis.page.PageBean;
import com.myproject.springmybatis.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class PersonController {

    Logger logger = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    PersonService personservice;

    @RequestMapping("/index")
    public String
    index(HttpSession session, Map map, @RequestParam(value = "currentPage",defaultValue = "1") int currentPage,@RequestParam(value = "pageSize",defaultValue = "5") int pageSize) {
        if (StringUtils.isEmpty((String) session.getAttribute("userName"))) {
            return "login";
        }
        String userName = (String) session.getAttribute("userName");
        map.put("userName", userName);
        PageBean<Person> page = personservice.pageAll(currentPage, pageSize);
        map.put("pageData", page);
//        List<Person> list = personservice.findAll();
//        map.put("list", list);
        return "index";
    }

    @RequestMapping("/add")
//    @ResponseBody
    public String add(Map map) {
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
    public String save(Person person, Map map) {
        personservice.addPerson(person);
        map.put("list", personservice.findAll());
        return "redirect:index";
    }

    @RequestMapping("/edit")
    public String getPerson(Long id, Model model) {
        Person person = personservice.getPersonById(id);
        model.addAttribute("person", person);
        return "person/personForm";
    }

    @RequestMapping("/delete")
    public String delete(Long id, Map map) {
        personservice.deleteById(id);
        map.put("list", personservice.findAll());
        return "redirect:index";
    }

    @RequestMapping("/register")
    public String register() {
        return "person/register";
    }

    @RequestMapping("/success")
    public String success(Model model, Person person) {

        personservice.addPerson(person);
        model.addAttribute("msg", "注册成功");
        return "success";
    }

    @RequestMapping("/form")
    public String form() {
        return "form";
    }

    @RequestMapping("/person/findByPage")
    public String pageAll(Map map,Person person,@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,@RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                          HttpSession session){
        if (person == null) {
            person = new Person();
        }else {
            if (!StringUtils.isEmpty(person.getUserName())){
                map.put("userName", person.getUserName());
            }
        }
        PageInfo<Person> page = personservice.findByPage(person,currentPage, pageSize);
        map.put("page", page);

        map.put("currentUser",session.getAttribute("userName"));

        return "person/personList";
    }

}
