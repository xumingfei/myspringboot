package com.myproject.springmybatis.service;

import com.myproject.springmybatis.model.Person;
import com.myproject.springmybatis.page.PageBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    List<Person> findAll();

    Person getPersonById(Long id);

    int deleteById(Long id);

    int addPerson(Person person);

    Person isValid(String userName, String password);

    int update(Person person);

    PageBean<Person> pageAll(int currentPage, int pageSize);
}
