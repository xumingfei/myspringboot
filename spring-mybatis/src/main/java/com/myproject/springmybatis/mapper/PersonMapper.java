package com.myproject.springmybatis.mapper;

import com.myproject.springmybatis.model.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PersonMapper {

    List<Person> findAll();

    Person getPersonById(Long id);

    int deleteById(Long id);

    int update(Person person);

    int addPerson(Person person);

    Person isValid(@Param("userName") String userName, @Param("password") String password);

    int countAll();

    List<Person> pageAll(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize);

    List<Person> findByPage(@Param("person") Person person, @Param("currentPage") int currentPage, @Param("pageSize") int pageSize);
}
