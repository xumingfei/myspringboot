package com.spring.redis.atguigu;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * @author: Xiaofei
 * @DATE: 2021/7/5 20:13
 */
public class JedisDemo1 {


    public static void main(String[] args) {
        // 创建Jedis对象

        Jedis jedis = new Jedis("192.168.0.110", 6379);

        // 测试
        String ping = jedis.ping();
        System.out.println(ping);


    }

    /**
     * 操作List
     */

    @Test
    public void demo2(){
        Jedis jedis = new Jedis("192.168.0.110", 6379);

        jedis.lpush("names", "java", "c++", "python");
        List<String> values = jedis.lrange("names", 0, -1);
        System.out.println(values);


    }

    /**
     * 操作set
     */
    @Test
    public void demo3(){
        Jedis jedis = new Jedis("192.168.0.110", 6379);

        jedis.sadd("englishName", "lucy", "jack");
        Set<String> values = jedis.smembers("englishName");
        System.out.println(values);
        jedis.srem("englishName", "lucy");
        System.out.println(jedis.smembers("englishName"));

    }

    /**
     * 操作hash
     */
    @Test
    public void demo4(){
        Jedis jedis = new Jedis("192.168.0.110", 6379);

        jedis.hset("hashName", "age", "20");
        String hget = jedis.hget("hashName", "age");
        System.out.println(hget);

    }


    /**
     * 操作String
     */
    @Test
    public void demo1(){
        Jedis jedis = new Jedis("192.168.0.110", 6379);
        // 设置
        jedis.set("name", "zhangsan");
        // 获取
        String name = jedis.get("name");
        System.out.println(name);
        Long expireTime = jedis.ttl("name");

        // 设置多个key-value
        jedis.mset("k1", "v1", "k2", "v2");

        List<String> mget = jedis.mget("k1", "k2");
        System.out.println(mget);

        System.out.println(expireTime);

        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }


    }
}
