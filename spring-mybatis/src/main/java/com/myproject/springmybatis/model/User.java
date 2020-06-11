package com.myproject.springmybatis.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/11 9:46
 */
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5032849657273785452L;
    private Long id;
    private String userName;
    private String sex;
    private String phonenumber;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 盐加密
     */
    private String salt;
    private String password;
    private Integer age;
    /**
     * 账号状态(0正常,1停用)
     */
    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userName", getUserName())
                .append("phonenumber", getPhonenumber())
                .append("createTime", getCreateTime())
                .append("avatar", getAvatar())
                .toString();
//        return "User{" +
//                "id=" + id +
//                ", userName='" + userName + '\'' +
//                ", sex='" + sex + '\'' +
//                ", phonenumber='" + phonenumber + '\'' +
//                ", avatar='" + avatar + '\'' +
//                ", salt='" + salt + '\'' +
//                ", password='" + password + '\'' +
//                ", age=" + age +
//                ", status='" + status + '\'' +
//                '}';
    }
}
