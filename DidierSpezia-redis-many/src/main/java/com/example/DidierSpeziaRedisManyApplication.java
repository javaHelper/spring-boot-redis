package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories(basePackages = "com.example.repository")
public class DidierSpeziaRedisManyApplication implements CommandLineRunner{
	
	
	public static void main(String[] args) {
		SpringApplication.run(DidierSpeziaRedisManyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
	
}
