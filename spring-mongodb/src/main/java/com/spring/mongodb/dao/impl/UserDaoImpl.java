package com.spring.mongodb.dao.impl;

import com.spring.mongodb.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/7 14:23
 */
@Repository
public class UserDaoImpl {
    @Autowired
    MongoTemplate mongoTemplate;

    public User findUserByIdOrUserName(Long id, String userName) {
        Criteria criteriaId = Criteria.where("id").is(id);
        Criteria criteria1UserName = Criteria.where("userName").is(userName);
        Criteria criteria = new Criteria();
        criteria.orOperator(criteriaId, criteria1UserName);
        Query query = Query.query(criteria);
        return mongoTemplate.findOne(query, User.class);
    }
}
