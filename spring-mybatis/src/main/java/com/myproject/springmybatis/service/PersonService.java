package com.myproject.springmybatis.service;

import com.myproject.springmybatis.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    public List<Person> findAll();

    public Person getPersonById(int id);

    public void deleteById(int id);

    public void addPerson(Person person);

    Person isValid(String userName, String password);

}
