package com.spring.redis.springredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/4 15:28
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${spring.redis.chanel}")
    private String chanel;

    /**
     * redis数据类型操作
     *
     * @return
     */
    @RequestMapping("/stringAndHash")
    public Map<String, Object> testStringAndHash() {
        redisTemplate.opsForValue().set("name", "张三");
        //由于使用了jdk的序列化器,所以redis保存时不是整数,不能运算
        redisTemplate.opsForValue().set("int_key", "1");
        stringRedisTemplate.opsForValue().set("int", "1");
        //使用运算
        stringRedisTemplate.opsForValue().increment("int", 1);
        //获取jedis连接
        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        jedis.decr("int");
        Map<String, String> hash = new HashMap<>();
        hash.put("field1", "value1");
        hash.put("field2", "value2");
        stringRedisTemplate.opsForHash().putAll("hash", hash);
        stringRedisTemplate.opsForHash().put("hash", "field3", "value3");

        //绑定散列操作的key,这样可以了连续对同一个散列数据类型进行操作
        BoundHashOperations hashOperations = stringRedisTemplate.boundHashOps("hash");
        hashOperations.delete("field1");
        hashOperations.put("field4", "value4");

        Map<String, Object> map = new HashMap<>();
//        stringRedisTemplate.opsForValue().
//        map.put()
        map.put("success", true);

        return map;
    }

    @RequestMapping("/list")
    public Map<String, Object> testList() {
        //插入两个列表,注意他们在链表中的顺序
        //链表从左到右顺序为10,8,6,4,2,
        stringRedisTemplate.opsForList().leftPushAll("list1", "v2", "v4", "v6", "v8", "v10");
        //链表从左到右顺序为123456
        stringRedisTemplate.opsForList().rightPushAll("list2", "v1", "v2", "v3", "v4", "v5", "v6");
        // 绑定list2链表操作
        BoundListOperations listOperations = stringRedisTemplate.boundListOps("list2");
        //从右边弹出一个成员
        Object result1 = listOperations.rightPop();

        //获得定位元素,Redis从0开始计算,这里值为v2
        Object result2 = listOperations.index(1);
        listOperations.leftPush("v0");
        Long size = listOperations.size();
        List elements = listOperations.range(0, size - 2);
        System.out.println(elements);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/set")
    public Map<String, Object> testSet() {
        stringRedisTemplate.opsForSet().add("set1", "v1", "v1", "v2", "v3", "v4", "v5");
        stringRedisTemplate.opsForSet().add("set2", "v2", "v4", "v6", "v8");
        BoundSetOperations setOperations = stringRedisTemplate.boundSetOps("set1");
        setOperations.add("v6", "v7");
        setOperations.remove("v1", "v7");
        Set members = setOperations.members();
        Long size = setOperations.size();
        //求交集
        Set set2 = setOperations.intersect("set2");

        //求交集并用新集合保存
        setOperations.intersectAndStore("set2", "inter");

        setOperations.diff("set2");
        setOperations.diffAndStore("set2", "diff");
        setOperations.union("set2");
        setOperations.unionAndStore("set2", "union");

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/zset")
    public Map<String, Object> testZSet() {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            //分数
            double score = i * 0.1;
            //创建一个TypedTuple对象存入值和分数
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<>("value" + i, score);
            typedTuples.add(typedTuple);
        }
        //往有序集合插入元素
        stringRedisTemplate
                .opsForZSet().add("zset1", typedTuples);
        //绑定zset1有序集合操作
        BoundZSetOperations zSetOperations = stringRedisTemplate.boundZSetOps("zset1");
        zSetOperations.add("value10", 0.26);
        Set setRange = zSetOperations.range(1, 6);
        //按分数排序获取有序集合
        Set setScore = zSetOperations.rangeByScore(0.2, 0.6);
        //定义值范围
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.gt("value3");//大于value3
//        range.gte("value3");//大于等于value3
//        range.lt("value8");//小于value8
        range.lte("value8");//小于等于value8
        //按值排序,请注意这个排序是按字符串排序
        Set setLex = zSetOperations.rangeByLex(range);
        //删除元素
        zSetOperations.remove("value9", "value2");
        //求分数
        Double score = zSetOperations.score("value8");
        //在下标区间下,按分数排序,同事返回value和score
        Set<ZSetOperations.TypedTuple<String>> rangeSet = zSetOperations.rangeWithScores(1, 6);
        //在分数区间下,按分数排序,同时返回value和score
        Set<ZSetOperations.TypedTuple<String>> scoreSet = zSetOperations.rangeByScoreWithScores(1, 6);
        //从小到大排序
        zSetOperations.reverseRange(2, 8);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    /**
     * redis事务操作
     * 通过Spring使用Redis事务机制
     */
    @RequestMapping("/multi")
    public Map<String, Object> testMutil() {
        redisTemplate.opsForValue().set("mkey1", "mvalue1");
        List list = (List) redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.watch("mkey1");
                redisOperations.multi();
                redisOperations.opsForValue().set("mkey2", "mvalue2");
//                redisOperations.opsForValue().increment("mkey1", 1);
                Object value2 = redisOperations.opsForValue().get("mkey2");
                System.out.println("命令在队列,所以value2为null[" + value2 + "]");
                redisOperations.opsForValue().set("mkey3", "mvalue3");
                Object value3 = redisOperations.opsForValue().get("mkey3");
                System.out.println("命令在队列,所以value3为null[" + value3 + "]");
                return redisOperations.exec();
            }
        });
        System.out.println(list);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;

    }

    /**
     * 使用Redis流水线测试性能
     */
    @RequestMapping("/pipeline")
    public Map<String, Object> testPipeline() {
        Long start = System.currentTimeMillis();
        List list = redisTemplate.executePipelined(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                for (int i = 0; i <= 100000; i++) {
                    operations.opsForValue().set("pipeline_" + i, "value_" + i);
                    String value = (String) operations.opsForValue().get("pipeline_" + i);
                    if (i == 100000) {
                        System.out.println("命令只是进入队列,所以值为空[" + value + "]");
                    }
                }
                return null;
            }
        });

        Long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start) + "毫秒");


        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    //测试发送消息
    @RequestMapping("/sendMsg")
    public Map<String, Object> testSendMsg() {
        String msg = "Hello,Redis Msg!";
        redisTemplate.convertAndSend(chanel, msg);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("success", true);
        return map;
    }

    @RequestMapping("/lua")
    public Map<String, Object> testLua() {
        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
        //设置脚本
        redisScript.setScriptText("return 'Hello Redis'");
        //定义返回类型.注意,如果没有这个定义,spring不会返回结果
        redisScript.setResultType(String.class);
        RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
        //执行lua脚本
        String str = (String) redisTemplate.execute(redisScript, stringRedisSerializer, stringRedisSerializer, null);
        Map<String, Object> map = new HashMap<>();
        map.put("str", str);
        return map;
    }

    //http://localhost/redis/lua2?key1=1&key2=2&value1=1&value2=1
    @RequestMapping("/lua2")
    public Map<String, Object> testLua2(String key1, String key2, String value1, String value2) {
        String lua = "redis.call('set',KEYS[1],ARGV[1]) \n" +
                "redis.call('set',KEYS[2],ARGV[2]) \n" +
                "local str1 = redis.call('get',KEYS[1]) \n" +
                "local str2 = redis.call('get',KEYS[2]) \n" +
                "if str1 == str2 then \n" +
                "return 1 \n" +
                "end \n" +
                "return 0 \n";
        System.out.println(lua);
        //结果返回为Long
        DefaultRedisScript<Long> rs = new DefaultRedisScript<>();
        rs.setScriptText(lua);
        rs.setResultType(Long.class);
        RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
        List<String> keyList = new ArrayList<>();
        keyList.add(key1);
        keyList.add(key2);
        // 传递两个参数值,其中第一个序列化器是key的序列化器,第二个序列化器是参数的序列化器
        Long result = (Long) redisTemplate.execute(rs, stringRedisSerializer, stringRedisSerializer, keyList, value1, value2);
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        return map;
    }



}
