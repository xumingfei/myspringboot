package com.wisely.springmvc.pojo;

import java.io.Serializable;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/8 11:23
 */
public class User implements Serializable {
    private static final long serialVersionUID = 103737850442944015L;
    private Long id;
    private String userName;
    private String password;
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
