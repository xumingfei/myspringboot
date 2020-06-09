package com.practice.spring.java.entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 511461220679457233L;

    private String name;
    private String password;
    private Integer age;
    private String phone;

    public User() {
    }

    public User(String name, String password, Integer age, String phone) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }
}
