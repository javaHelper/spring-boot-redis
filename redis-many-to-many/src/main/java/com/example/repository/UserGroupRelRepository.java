package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.model.UserGroupRel;

public interface UserGroupRelRepository extends CrudRepository<UserGroupRel, String>{

	List<UserGroupRel> findByGroupId(String groupId);
	List<UserGroupRel> findByUserId(String userId);
}
