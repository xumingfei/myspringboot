package com.spring.mongodb.dao;

import com.spring.mongodb.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/7 14:15
 */
@Repository
public interface UserDao extends MongoRepository<User,Long> {

    /**
     * 符合JPA规范命名方法,不需要再实现该方法也可用
     * 意在对满足条件的文档按照用户名称进行模糊查询
     * @param userName 用户名称
     * @return 满足用户条件的信息
     */
    List<User> findByUserNameLike(String userName);

    /**
     * 使用id和userName查询
     * 注解Query阿拉伯数字制定参数下标,以0开始
     * @param id 编号
     * @param userName
     * @return 用户信息
     */
    @Query("{'id', ?0,'userName':?1}")
    User find(Long id, String userName);

    /**
     * 自定义方法根据id和username查询
     * @param id
     * @param userName
     * @return
     */
    User findUserByIdOrUserName(Long id, String userName);
}
