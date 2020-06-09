package com.spring.redis.springredis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableCaching //启用缓存机制
@MapperScan(basePackages = "com.spring.redis.springredis.mapper")
public class SpringRedisApplication {

	@Autowired
	private RedisTemplate redisTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringRedisApplication.class, args);
	}

	//定义自定义后初始化方法
	@PostConstruct
	public void init(){
		initRedisTemplate();
	}

	//设置RedisTemplate的序列化器
	private void initRedisTemplate() {
		RedisSerializer serializer = redisTemplate.getStringSerializer();
		redisTemplate.setKeySerializer(serializer);
		redisTemplate.setHashKeySerializer(serializer);
		redisTemplate.setValueSerializer(serializer);
		redisTemplate.setHashValueSerializer(serializer);
	}

}
