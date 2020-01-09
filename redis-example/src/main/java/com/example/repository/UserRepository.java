package com.example.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.example.model.User;

public interface UserRepository extends QueryByExampleExecutor<User>, CrudRepository<User, String> {
	
}