package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("user")
public class User {
	@Id
	@Indexed
	private String id;
	@Indexed
	private String firstName;
	@Indexed
	private String emailId;
}
