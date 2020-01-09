package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Data;


@Data
@RedisHash("User")
public class User {
 
    private @Id Long id;
    @Indexed
    private String firstname; 
    @Indexed
    private String lastname;
    private Integer age;
    
    public User() {}

	public User(String firstname, String lastname, Integer age) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
	}
}