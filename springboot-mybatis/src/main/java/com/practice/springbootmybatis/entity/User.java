package com.practice.springbootmybatis.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.UUID;

public class User extends BaseEntity{

    private static final long serialVersionUID = -6030221094237155860L;

    private String id;
    private String userName;
    private Integer age;
    private String pwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
//        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
//                .append("id",getId())
//                .append("userName",getUserName())
//                .toString();
        return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE).toString();
    }

    public static void main(String[] args) {
//        String a = new ToStringBuilder(this.).
        User user = new User();
        user.setAge(10);
        user.setId(UUID.randomUUID().toString().replace("-",""));
        user.setUserName("张三");
        System.out.println(user.toString());
    }
}
