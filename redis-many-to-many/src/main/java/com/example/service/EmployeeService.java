package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public List<Employee> findEmployessByProductCategory(String productCategory) {
		return employeeRepository.findByDepartments_Projects_ProjectCategory("Social");
	}
}
