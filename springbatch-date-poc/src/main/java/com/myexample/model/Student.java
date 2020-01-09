package com.myexample.model;

import java.time.LocalDateTime;
import java.util.Date;

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
@RedisHash("student")
public class Student {
	@Id @Indexed
	private String id;
	private String firstName;
	private String lastName;
	private LocalDateTime dateOfBirth;
}
