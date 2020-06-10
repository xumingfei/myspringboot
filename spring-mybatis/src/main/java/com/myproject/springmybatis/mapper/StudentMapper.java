package com.myproject.springmybatis.mapper;

import com.myproject.springmybatis.model.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> findAll();
    void add(Student student);
    void delete(String id);
    void update(Student student);
}
