package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.model.User;

public interface UserRepository extends CrudRepository<User, String>{

	List<User> getUserById(String name);
	
	List<String> getIdByFirstName(String firstName);
}
