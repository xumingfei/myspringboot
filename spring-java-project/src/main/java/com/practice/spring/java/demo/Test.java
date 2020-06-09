package com.practice.spring.java.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) {
//        Test.reverse("abcd");
//        String str = "   abcdefghijklmnopqrstuvwxyz  ";
//        System.out.println(str);
//        System.out.println(str.trim());
//        System.out.println(str.split("-").toString());

        Map<String,String> map = new HashMap<String,String>();
        System.out.println(map.put("123",""));
        map.put("123","aaa");
        System.out.println(map.put("123","def"));
        System.out.println(map.put("123","def"));
        map.put("123","def");

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        String a = "123";
        String b = "123";
        System.out.println(a==b);


//        String str1="通话";
//        String str2="重地";
//        System.out.println(String.format("str1:%d | str2: %d",str1.hashCode(),str2.hashCode()));
//        System.out.println(str1.equals(str2));

    }

    public static void testEquals(){
        String x = "string";
        String y = "string";
        String z = new String("string");

        System.out.println(x==y);
        System.out.println(x==z);
        System.out.println(x.equals(y));
        System.out.println(x.equals(z));
    }

    public static void reverse(String str){
        StringBuffer sb = new StringBuffer();
        sb.append(str);

        System.out.println(sb.reverse());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        System.out.println(stringBuilder.reverse());
    }
}
