package com.myproject.springmybatis.model;

public class Person {

    private Long id;
    private String userName;
    private String mobile;
    private String password;
    private Integer age;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person(Long id, String userName, String mobile, Integer age) {
        this.id = id;
        this.userName = userName;
        this.mobile = mobile;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", age=" + age +
                '}';
    }
}
