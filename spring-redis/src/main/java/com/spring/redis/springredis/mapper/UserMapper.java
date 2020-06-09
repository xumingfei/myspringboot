package com.spring.redis.springredis.mapper;

import com.spring.redis.springredis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/5 9:45
 */
public interface UserMapper {
    User getUser(Long id);

    int insertUser(User user);

    int updateUser(User user);

    List<User> findUsers(@Param("userName") String userName, @Param("note") String note);

    int deleteUser(Long id);

}
