package com.wisely.springmvc.pojo;

import java.io.Serializable;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/10 14:01
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = 6273420245696987353L;
    private Integer id;
    private String loginname;
    private String nickname;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Contomer{" +
                "id=" + id +
                ", loginname='" + loginname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
