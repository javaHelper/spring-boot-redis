package com.example.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisConfig {
	@Autowired
	private Environment env;

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setDatabase(Integer.parseInt(env.getProperty("spring.redis.database")));
		configuration.setHostName(env.getProperty("spring.redis.host"));
		configuration.setPort(Integer.parseInt(env.getProperty("spring.redis.port")));

		JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
		builder.connectTimeout(Duration.ofSeconds(60));
		return new JedisConnectionFactory(configuration);
	}
	
	@Bean
	public RedisTemplate<String, String> redisTemplate() {
		final RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
		//return new StringRedisTemplate(jedisConnectionFactory());
	}
}
