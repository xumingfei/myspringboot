package com.practice.crud.controller;

import com.practice.crud.dao.StudentDao;
import com.practice.crud.entity.Student;
import com.practice.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/stud")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentDao studentDao;

    @PostMapping("/add")
    public Student save(Student student){
        return studentService.save(student);
    }

    @PostMapping("/update")
    public Student update(Student student){
        return studentService.update(student);
    }

    @GetMapping("/del/{id}")
    public String del(@PathVariable int id){
        studentService.delete(id);
        return "true";
    }

    @GetMapping("/findByName/{name}")
    public List<Student> findByName(@PathVariable String name){
        return studentService.findStuByName(name);
    }

    @GetMapping("/page")
    public Page<Student> findByPage(Integer page, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","");
        if(page == null|| page <= 0){
            page = 0;
        }else {
            page -= 1;
        }
        return studentService.findAll(page,5);
    }

    @RequestMapping("/findAll")
    public List<Student> findAll(){
        return studentService.findAll();
    }



}
