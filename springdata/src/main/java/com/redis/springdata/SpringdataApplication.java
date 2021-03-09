package com.redis.springdata;

import com.redis.springdata.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class SpringdataApplication {

	@Bean
	JedisConnectionFactory connectionFactory() {

		return new JedisConnectionFactory();
	}

	@Bean
	@Primary
	RedisTemplate<String, User> redisTemplate(){
		RedisTemplate<String,User> redisTemplate = new RedisTemplate<String, User>();
		redisTemplate.setConnectionFactory(connectionFactory());
		return redisTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringdataApplication.class, args);
	}

}
