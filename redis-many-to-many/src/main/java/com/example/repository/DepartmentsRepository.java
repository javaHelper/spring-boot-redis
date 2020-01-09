package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Department;

public interface DepartmentsRepository extends CrudRepository<Department, String>{

	List<Department> findByDepartmentName(String departmentName);
}
