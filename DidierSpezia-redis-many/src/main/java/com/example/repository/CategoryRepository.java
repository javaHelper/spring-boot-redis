package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Category;

public interface CategoryRepository extends CrudRepository<Category, String>{
	
	Category getCategoryById(String id);
}
