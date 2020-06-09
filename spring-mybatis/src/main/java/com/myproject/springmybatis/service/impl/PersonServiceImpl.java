package com.myproject.springmybatis.service.impl;

import com.myproject.springmybatis.dao.PersonDao;
import com.myproject.springmybatis.model.Person;
import com.myproject.springmybatis.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

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
    public Person getPersonById(Long id) {
        return personDao.getPersonById(id);
    }

    @Override
    public int deleteById(Long id) {
        return personDao.deleteById(id);
    }

    @Override
    public int addPerson(Person person) {
        int count = 0;
        if (StringUtils.isEmpty(person.getPassword())) {
            person.setPassword("123456");
        }
        count = personDao.addPerson(person);
        System.out.println(count);
        return count;
    }

    @Override
    public Person isValid(String userName, String password) {
        Person person = personDao.isValid(userName, password);
        return person;
    }

    @Override
    public int update(Person person) {
        Person obj = this.getPersonById(person.getId());
        int count = 0;
        if (obj != null) {
            count = personDao.update(person);
        } else {
            if (StringUtils.isEmpty(person.getPassword())) {
                person.setPassword("123456");
            }
            count = this.addPerson(person);
        }
        return count;
    }


}
