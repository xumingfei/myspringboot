package com.practice.crud.service.impl;

import com.practice.crud.dao.StudentDao;
import com.practice.crud.entity.Student;
import com.practice.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentDao studentDao;


    @Override
    public Student save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public Student update(Student student) {
        return studentDao.save(student);
    }

    @Override
    public void delete(Integer id) {
        studentDao.deleteById(id);
    }

    @Override
    public Student findStuById(Integer id) {
        return studentDao.findStudentById(id);
    }

    @Override
    public List<Student> findStuByName(String name) {
        return studentDao.findStuByName(name);
    }

    @Override
    public Page<Student> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return studentDao.findAll(pageable);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll(Sort.by(Sort.Order.asc("name")));
    }
}
