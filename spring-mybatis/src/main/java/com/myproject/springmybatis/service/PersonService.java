package com.myproject.springmybatis.service;

import com.github.pagehelper.PageInfo;
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

    /**
     * 自定义分页插件进行分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<Person> pageAll(int currentPage, int pageSize);

    /**
     * 使用分页插件进行分页
     * @param person
     * @param curretPage
     * @param pageSize
     * @return
     */
    PageInfo<Person> findByPage(Person person, int curretPage, int pageSize);
}
