package com.example.model;

import java.util.List;

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
@RedisHash("employees")
public class Employee {
	@Id @Indexed
	private String id;
	private String firstName;
	private String lastName;
	private List<Address> addresses;
	
	private List<Department> departments;
}
