package com.myproject.springmybatis.controller;

import com.myproject.springmybatis.model.Student;
import com.myproject.springmybatis.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @RequestMapping("/addStu")
    @ResponseBody
    public String add(){
        Student student= new Student();
        student.setAge(10);
        student.setName("123");
        student.setPersonId("");
        studentService.add(student);
        logger.info("新增学生");
        return "success";
    }
}
