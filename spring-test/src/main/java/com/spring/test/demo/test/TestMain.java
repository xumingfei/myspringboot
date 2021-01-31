package com.spring.test.demo.test;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author: Xiaofei
 * @DATE: 2021/1/14 20:15
 */
public class TestMain {
    public static void main(String[] args) {
        System.out.println(DateUtil.date());
        System.out.println(DateUtil.format(new Date(),"yyyy-MM-dd hh:mm:ss"));
        System.out.println(DateUtil.year(new Date()));
    }
}
