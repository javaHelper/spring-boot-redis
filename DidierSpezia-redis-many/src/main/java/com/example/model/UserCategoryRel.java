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
@RedisHash("CategoryOfUsers")
public class UserCategoryRel {
	@Id
	private String id; 
	@Indexed
	private String userId;
	@Indexed
	private String categoryId;
}
