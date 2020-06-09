package com.spring.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/7 12:05
 */
@SpringBootApplication
@EnableMongoRepositories(
        //扫描包
        basePackages = "com.spring.mongodb.dao",
//默认是impl,修改为其他值后对应的类名称后缀则必须改为它
repositoryImplementationPostfix = "impl")
public class MongodbMain {
    public static void main(String[] args) {
        SpringApplication.run(MongodbMain.class, args);
    }
}
