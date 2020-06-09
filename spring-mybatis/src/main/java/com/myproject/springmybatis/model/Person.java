package com.myproject.springmybatis.model;

public class Person {
    private int id;
    private String userName;
    private String mobile;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void say(){
        System.out.println("Hello,Person");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person() {
    }

    public Person(int id, String userName, String mobile) {
        this.id = id;
        this.userName = userName;
        this.mobile = mobile;
    }

    public Person(int id, String userName, String mobile, String password) {
        this.id = id;
        this.userName = userName;
        this.mobile = mobile;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
