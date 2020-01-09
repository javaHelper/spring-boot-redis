package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;
import com.example.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = "application/json")
@Api(tags = "Employee Catalouge", value = "Manage Emplyeees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@ApiOperation(value = "Create a new Employee", nickname = "Create new Employee")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully added a new Employee"),
			@ApiResponse(code = 400, message = "Required fields not provided")
	})
	@PostMapping("/create-employee")
	public Employee createProduct(@RequestBody Employee employee){
		return employeeService.createEmployee(employee);
	}
	
	
	@ApiOperation(value = "Retrieve Employee by ProjectCategory", nickname = "Find Employee by ProjectCategory")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved product by Id"),
            @ApiResponse(code = 404, message = "No data found !!!")
    })
    @GetMapping(path = "/employees/{productCategory}")
	public List<Employee> findEmployessByProductCategory(@PathVariable("productCategory") String productCategory){
		return employeeService.findEmployessByProductCategory(productCategory);
	}
}
