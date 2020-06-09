package com.spring.redis.springredis;

import com.spring.redis.springredis.service.RedisStringService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRedisApplicationTests {

	@Autowired
    RedisStringService stringService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void setString(){
		stringService.setString("a","123");
	}

	@Test
	public void getString(){
		stringService.getString("a");
	}

	@Test
	public void hello() throws Exception {

	}

}
