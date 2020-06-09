package com.spring.redis.springredis.service.impl;

import com.spring.redis.springredis.mapper.UserMapper;
import com.spring.redis.springredis.pojo.User;
import com.spring.redis.springredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/5 10:00
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional
    @Cacheable(value = "redisCache",key = "'redis_user_'+#id")
    public User getUser(Long id) {
        return userMapper.getUser(id);
    }

    @Override
    @Transactional
    @CachePut(value = "redisCache",key = "'redis_user_'+#result.id")
    public User insertUser(User user) {
        userMapper.insertUser(user);
        return user;
    }

    @Override
    @Transactional
    @CachePut(value = "redisCache", condition = "#result != 'null' ",key = "'redis_user_'+#id")
    public User updateUserName(Long id, String userName) {
        //此处调用getuser方法,该方法缓存注解失效(自调用方法没有使用代理,不会进行aop注入,所以注解失效)
        //所以这里还会执行sql,将查询到数据库最新数据
        User user = this.getUser(id);
        if (user == null) {
            return null;
        }
        user.setUserName(userName);
        userMapper.updateUser(user);
        return user;
    }

    // 命中率低,所以不采用缓存机制
    @Transactional
    @Override
    public List<User> findUsers(String userName, String note) {
        return userMapper.findUsers(userName,note);
    }

    @Override
    @Transactional
    @CacheEvict(value = "redisCache", key = "'redis_user_'+#id",beforeInvocation = false)
    public int deleteUser(Long id) {
        return userMapper.deleteUser(id);
    }
}
