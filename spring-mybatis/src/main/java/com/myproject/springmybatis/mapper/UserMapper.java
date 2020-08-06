package com.myproject.springmybatis.mapper;

import com.myproject.springmybatis.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/11 10:37
 */
@Mapper
public interface UserMapper {
    String getPassword(@Param("userName") String userName);

    User getUserByLoginName(@Param("userName") String userName);

    int save(@Param("user") User user);
}
