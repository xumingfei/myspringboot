package com.spring.mongodb.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.spring.mongodb.pojo.User;
import com.spring.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/7 12:16
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(User user) {
        // 使用名称为user文档保存用户信息
        mongoTemplate.save(user, "user");
        //如果文档采用类名首字符小写,则可以
        mongoTemplate.save(user);
    }

    @Override
    public DeleteResult deleteUser(Long id) {
        Criteria criteriaId = Criteria.where("id").is(id);
        Query query = Query.query(criteriaId);
        DeleteResult result = mongoTemplate.remove(query, User.class);
        return result;
    }

    @Override
    public List<User> findUser(String userName, String note, int skip, int limit) {
        Criteria criteria = Criteria.where("userName").regex(userName)
                .and("note").regex(note);
        Query query = Query.query(criteria).limit(limit).skip(skip);
        List<User> userList = mongoTemplate.find(query, User.class);
        return userList;
    }

    @Override
    public UpdateResult updateUser(Long id, String userName, String note) {
        Criteria criteria = Criteria.where("id").is(id);
        Query query = Query.query(criteria);
        Update update = Update.update("userName", userName);
        update.set("note", note);
        // 更新多个对象
//        mongoTemplate.updateMulti(query, update, User.class);
        return mongoTemplate.updateFirst(query,update,User.class);
    }

    @Override
    public User getUser(Long id) {
//        return mongoTemplate.findById(id, User.class);

        //如果只需要获取第一个,可以使用如下查询
        Criteria criteriaId = Criteria.where("id").is(id);
        Query queryId = Query.query(criteriaId);
        return mongoTemplate.findOne(queryId, User.class);
    }
}
