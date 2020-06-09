package com.spring.mongodb.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/7 12:06
 */
@Document //标识为MongoDB文档
public class User implements Serializable {

    private static final long serialVersionUID = -6800527511107610016L;

    // MongoDB文档编号,主键
    @Id
    private Long id;

    //Mongodb中使用username
    @Field("username")
    private String userName;

    private String note;

    private List<Role> roles;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", note='" + note + '\'' +
                ", roles=" + roles +
                '}';
    }
}
