package com.practice.spring.java.demo;

/**
 * 饿汉式单例模式
 */
public class Singleton {
    private Singleton(){}

    private static volatile Singleton instance = null;

    public Singleton getInstance(){
        if(instance!=null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;

    }
}
