package com.myproject.springmybatis.controller;

import com.github.pagehelper.PageInfo;
import com.myproject.springmybatis.model.Person;
import com.myproject.springmybatis.model.User;
import com.myproject.springmybatis.page.PageBean;
import com.myproject.springmybatis.service.PersonService;
import com.myproject.springmybatis.service.UserService;
import com.myproject.springmybatis.util.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Controller
public class PersonController {

    Logger logger = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    PersonService personservice;

    @Autowired
    UserService userService;

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
    public String save(Person person, Map map,@RequestParam("files") MultipartFile[] files) throws IOException {
        System.out.println(files);
        String filePath = "D:\\file\\";
        if (files != null) {
            for (MultipartFile file :
                    files) {
                if (!file.isEmpty()) {
                    file.transferTo(new File(filePath+file.getOriginalFilename()));
                }
            }
        }
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

    @RequestMapping("/success")
    public String success(Model model, Person person) {

        personservice.addPerson(person);
        User user = new User();
        user.setAge(person.getAge());
        user.setUserName(person.getUserName());
        user.setPhonenumber(person.getMobile());
        String password = ShiroUtils.MD5Pwd(person.getUserName(),person.getPassword());
        user.setPassword(password);
        userService.save(user);
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
