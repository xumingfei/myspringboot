package com.practice.crud.dao;

import com.practice.crud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentDao extends JpaRepository<Student,Integer>{

    Student findStudentById(Integer id);

    @Query(name="findStuByName",nativeQuery = true,value = "select * from student where NAME =:name ")
    List<Student> findStuByName(@Param("name") String name);
}
