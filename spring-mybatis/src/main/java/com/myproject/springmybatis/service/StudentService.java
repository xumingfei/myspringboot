package com.myproject.springmybatis.service;

import com.myproject.springmybatis.dao.StudentDao;
import com.myproject.springmybatis.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    public List<Student> findAll(){
        return studentDao.findAll();
    }

    public void add(Student student){
        studentDao.add(student);
    }

    public void update(Student student){
        studentDao.update(student);
    }

    public void delete(String id){
        studentDao.delete(id);
    }
}
