package com.practice.spring.java.demo;

public class SingletonPatternDemo {
    public static void main(String[] args) {
        SingleObject object = SingleObject.getInstance();
        object.showMessage();
        
    }
}
