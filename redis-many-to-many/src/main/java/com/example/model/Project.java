package com.example.model;

import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
	@Indexed
	private String projectName;
	@Indexed
	private String projectCategory;
}
