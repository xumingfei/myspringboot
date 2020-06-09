package com.practice.springbootmybatis.mapper;

import com.practice.springbootmybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//@Repository
public interface UserMapper {
    User getUserById(Long id);

    void delById(Long id);

    void add(User user);

    void update(User user);
}
