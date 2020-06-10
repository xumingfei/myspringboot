package com.myproject.springmybatis.service;

import com.myproject.springmybatis.mapper.StudentMapper;
import com.myproject.springmybatis.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    public List<Student> findAll(){
        return studentMapper.findAll();
    }

    public void add(Student student){
        studentMapper.add(student);
    }

    public void update(Student student){
        studentMapper.update(student);
    }

    public void delete(String id){
        studentMapper.delete(id);
    }
}
