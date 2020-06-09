package com.practice.spring.java.ref;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

public class TestMain {
    private static final Logger logger = LoggerFactory.getLogger(TestMain.class);
    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = Class.forName("com.practice.spring.java.entity.User");
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor c:constructors
             ) {
            logger.info(c.getName());
//            logger.info(c.);
        }
    }
}
