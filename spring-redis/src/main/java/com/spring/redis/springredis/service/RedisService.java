package com.spring.redis.springredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/4 11:26
 */
@Service
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    //获取地理位置操作接口
    public GeoOperations<Object, Object> forGeo() {
        return redisTemplate.opsForGeo();
    }

    //获取散列操作接口
    public HashOperations<Object, Object, Object> forHash() {
        return redisTemplate.opsForHash();
    }

    //获取基数操作接口
    public HyperLogLogOperations<Object, Object> forHyperLogLog() {
        return redisTemplate.opsForHyperLogLog();
    }

    //获取列表操作接口
    public ListOperations forList() {
        return redisTemplate.opsForList();
    }

    //获取集合操作接口
    public SetOperations<Object, Object> forSet() {
        return redisTemplate.opsForSet();
    }

    //获取字符串操作接口
    public ValueOperations forValue() {
        return redisTemplate.opsForValue();
    }

    //获取有序集合操作类接口
    public ZSetOperations<Object, Object> forZSet() {
        return redisTemplate.opsForZSet();
    }

    //通过RedisCallback在同一条连接夏之星多个redis命令
    //需要处理底层的转换规则,如果不考虑改写底层,尽量不适用他
    /*public void useRedisCallback(RedisTemplate redisTemplate) {
//        redisTemplate.execute(new RedisCallback() {
//            @Override
//            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
//                redisConnection.set("key1".getBytes(),"value1".getBytes());
//                redisConnection.hSet("hash".getBytes(), "field".getBytes(), "hvalue".getBytes());
//                return null;
//            }
//        });
        //Lambda
        redisTemplate.execute((RedisConnection redisConnection)->{
            redisConnection.set("key1".getBytes(),"value1".getBytes());
            redisConnection.hSet("hash".getBytes(), "field".getBytes(), "hvalue".getBytes());
            return null;
        });
    }*/

    //高级接口比较友好,一般情况下,优先使用他
    public void useSessionCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.opsForValue().set("key1","value1");
                redisOperations.opsForHash().put("hash","field","hvalue");
                return null;
            }
        });
       /* redisTemplate.execute((RedisOperations redisOperations)-> {
            redisOperations.opsForValue().set("key1","value1");
            redisOperations.opsForHash().put("hash","field","hvalue");
                return null;
        });*/
    }

    /*public void useSessionCallback2(){
        redisTemplate.execute((RedisOperations redisOperations)-> {
            redisOperations.opsForValue().set("key1","value1");
            redisOperations.opsForHash().put("hash","field","hvalue");
            return null;
        });
    }

    public void useRedisCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute((RedisConnection rc) -> {
            rc.set("key1".getBytes(), "value1".getBytes());
            rc.hSet("hash".getBytes(), "field".getBytes(), "hvalue".getBytes());
            return null;
        });
    }*/

    public void useRedisCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute((RedisConnection rc) -> {
            rc.set("key1".getBytes(), "value1".getBytes());
            rc.hSet("hash".getBytes(), "field".getBytes(), "hvalue".getBytes());
            return null;
        });
    }

    //TODO 报错
    /*public void useSessionCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute((RedisOperations ro) -> {
            ro.opsForValue().set("key1", "value1");
            ro.opsForHash().put("hash", "field", "hvalue");
            return null;
        });
    }*/
    public void useSessionCallback222(RedisOperations redisOperations) {
        redisTemplate.execute((RedisCallback) connection->{
            redisOperations.opsForValue().set("123","123");
            return null;
        });
    }



}
