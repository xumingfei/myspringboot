package com.spring.test.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Xiaofei
 * @DATE: 2020/12/21 15:23
 */
public class Test2 {
    public static void main(String[] args) {
        List<String> str = new ArrayList<>();
        for (int i=0;i<10;i++){
            str.add(0,String.valueOf(i));
        }
        for (String s:str){
            System.out.println(s);
        }

        str.add("2");
        str.add("3");
        System.out.println(str.toArray());

    }
}
