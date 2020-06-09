package com.practice.crud.dao;

import com.practice.crud.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User,Long>{
}
