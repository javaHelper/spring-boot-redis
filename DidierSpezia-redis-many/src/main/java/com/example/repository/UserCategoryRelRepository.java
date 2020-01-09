package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.model.UserCategoryRel;

public interface UserCategoryRelRepository extends CrudRepository<UserCategoryRel, String>{

	List<UserCategoryRel> getByUserId(String userId);
	
	List<UserCategoryRel> getByCategoryId(String categoryId);
}
