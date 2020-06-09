package com.practice.crud.service;

import com.practice.crud.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    Student save(Student student);
    Student update(Student student);
    void delete(Integer id);
    Student findStuById(Integer id);
    List<Student> findStuByName(String name);

    Page<Student> findAll(int page,int pageSize);

    List<Student> findAll();
}
