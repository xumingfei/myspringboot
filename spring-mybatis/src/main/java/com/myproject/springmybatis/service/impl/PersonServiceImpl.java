package com.myproject.springmybatis.service.impl;

import com.myproject.springmybatis.dao.PersonDao;
import com.myproject.springmybatis.model.Person;
import com.myproject.springmybatis.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/8 14:29
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonDao personDao;

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public Person getPersonById(int id) {
        return personDao.getPersonById(id);
    }

    @Override
    public void deleteById(int id) {
        personDao.deleteById(id);
    }

    @Override
    public void addPerson(Person person) {
        personDao.addPerson(person);
    }

    @Override
    public Person isValid(String userName, String password) {
        Person person = personDao.isValid(userName, password);
        return person;
    }
}
