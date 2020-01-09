package com.example.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@ConfigurationProperties
@Slf4j
@EnableRedisRepositories(basePackages = "com.example.repository")
public class RedisConfig {
	/*@Value("${spring.redis.ip}")
    private String redisIp;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.password}")
    private String password;*/
		

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
    	RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    	redisStandaloneConfiguration.setHostName("localhost");
    	redisStandaloneConfiguration.setPort(6379);
    	redisStandaloneConfiguration.setDatabase(0);
    	//redisStandaloneConfiguration.setPassword(RedisPassword.of("password"));

    	JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
    	jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));// 60s connection timeout

    	return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

  /*  @Bean
    RedisTemplate<Object, Object> redisTemplate() {
        final RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    }*/
    
    @Bean
    RedisTemplate<String, String> stringRedisTemplate() {
    	return new StringRedisTemplate(jedisConnectionFactory());
    }
}
