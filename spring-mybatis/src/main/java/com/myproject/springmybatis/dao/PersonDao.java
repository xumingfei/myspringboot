package com.myproject.springmybatis.dao;

import com.myproject.springmybatis.model.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PersonDao {

    List<Person> findAll();

    Person getPersonById(int id);

    void deleteById(int id);

    void update(Person person);

    void addPerson(Person person);

    Person isValid(@Param("userName") String userName, @Param("password") String password);
}
