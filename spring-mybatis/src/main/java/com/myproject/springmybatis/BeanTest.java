package com.myproject.springmybatis;

import com.myproject.springmybatis.model.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
        Person p = ctx.getBean("person", Person.class);
        p.say();


    }
}
