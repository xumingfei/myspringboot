package com.myproject.springmybatis.service.impl;

import com.myproject.springmybatis.mapper.PersonMapper;
import com.myproject.springmybatis.model.Person;
import com.myproject.springmybatis.page.PageBean;
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
    PersonMapper personMapper;

    @Override
    public List<Person> findAll() {
        return personMapper.findAll();
    }

    @Override
    public Person getPersonById(Long id) {
        return personMapper.getPersonById(id);
    }

    @Override
    public int deleteById(Long id) {
        return personMapper.deleteById(id);
    }

    @Override
    public int addPerson(Person person) {
        int count = 0;
        if (StringUtils.isEmpty(person.getPassword())) {
            person.setPassword("123456");
        }
        Person obj = this.getPersonById(person.getId());
        if (obj != null) {
            count = personMapper.update(person);
        }else {
            count = personMapper.addPerson(person);
        }
        System.out.println(count);
        return count;
    }

    @Override
    public Person isValid(String userName, String password) {
        Person person = personMapper.isValid(userName, password);
        return person;
    }

    @Override
    public int update(Person person) {
        Person obj = this.getPersonById(person.getId());
        int count = 0;
        if (obj != null) {
            count = personMapper.update(person);
        } else {
            if (StringUtils.isEmpty(person.getPassword())) {
                person.setPassword("123456");
            }
            count = this.addPerson(person);
        }
        return count;
    }

    @Override
    public PageBean<Person> pageAll(int currentPage, int pageSize) {
        int count = personMapper.countAll();
        List<Person> list = personMapper.pageAll(currentPage,pageSize);
        PageBean<Person> pageBean = new PageBean(list,count);
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        return pageBean;
    }


}
