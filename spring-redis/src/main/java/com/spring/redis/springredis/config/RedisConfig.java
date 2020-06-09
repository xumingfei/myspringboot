package com.spring.redis.springredis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/4 9:45
 * springgboot项目中可以直接使用配置文件
 */

@Configuration
public class RedisConfig {

    //Redis连接工厂
    private RedisConnectionFactory connectionFactory;

    @Autowired
    private MessageListener messageListener;
    @Value("${spring.redis.chanel}")
    private String chanel;
    //任务池
    private ThreadPoolTaskScheduler taskScheduler;

    @Bean(name = "redisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFaction() {
        if (this.connectionFactory != null) {
            return this.connectionFactory;
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //最大空闲数
        poolConfig.setMaxIdle(30);
        //最大连接数
        poolConfig.setMaxTotal(50);
        //最大等待毫秒数
        poolConfig.setMaxWaitMillis(2000);
        //创建Jedis工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
        //获取单机的Redis配置
        RedisStandaloneConfiguration rsCfg = connectionFactory.getStandaloneConfiguration();
        connectionFactory.setHostName("127.0.0.1");
        connectionFactory.setDatabase(0);
        connectionFactory.setPort(6379);
        connectionFactory.setPassword("");
       /* //获得默认的连接池构造器(怎么设计的，为什么不抽象出单独类，供用户使用呢)
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
        //指定jedisPoolConifig来修改默认的连接池构造器（真麻烦，滥用设计模式！）
        jpcb.poolConfig(poolConfig);
        //通过构造器来构造jedis客户端配置
        JedisClientConfiguration jedisClientConfiguration = jpcb.build();
        //单机配置 + 客户端配置 = jedis连接工厂
         new JedisConnectionFactory(rsCfg, jedisClientConfiguration);*/
         this.connectionFactory = connectionFactory;
         return connectionFactory;
    }

    @Bean
    public RedisTemplate<Object,Object> redisTemplate() {
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();
        //RedisTemplate会自动初始化StringRedisSerializer,所以这里直接获取
        RedisSerializer redisSerializer = redisTemplate.getStringSerializer();
        //设置字符串序列化器,这样Spring就会把Redis的key当作字符串处理了
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        redisTemplate.setConnectionFactory(initRedisConnectionFaction());
        return redisTemplate;
    }

    /**
     * 创建任务池,运行线程等待处理Redis的消息
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        if (taskScheduler != null) {
            return taskScheduler;
        }
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

    /**
     * redis消息监听容器
     * @return
     */
    @Bean
    public RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        //Redis连接工厂
        container.setConnectionFactory(connectionFactory);
        //设置运行任务池
        container.setTaskExecutor(taskScheduler());

        //定义监听渠道,名称为topic1
        Topic topic = new ChannelTopic(chanel);
        //使用监听器监听Redis消息
        container.addMessageListener(messageListener,topic);
        return container;
    }

    //自定义Redis缓存管理器
    @Bean
    public RedisCacheManager redisCacheManager(){
        //Redis加锁的写入器
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
        //启动Redis缓存的默认配置
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        //设置JDK序列化器
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));
        //禁用前缀
        redisCacheConfiguration = redisCacheConfiguration.disableKeyPrefix();
        //设置10min超时
        redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofMillis(10));

        RedisCacheManager redisCacheManager = new RedisCacheManager(redisCacheWriter,redisCacheConfiguration);
        return redisCacheManager;
    }
}
