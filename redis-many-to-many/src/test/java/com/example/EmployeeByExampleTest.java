package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.redis.core.PartialUpdate;
import org.springframework.data.redis.core.convert.PathIndexResolver;
import org.springframework.data.redis.core.mapping.RedisMappingContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.model.Address;
import com.example.model.Department;
import com.example.model.Employee;
import com.example.model.Project;
import com.example.repository.DepartmentsRepository;
import com.example.repository.EmployeeRepository;
import com.example.test.config.RepositoryTestSupport;
import com.fasterxml.jackson.core.JsonProcessingException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeByExampleTest extends RepositoryTestSupport{
	@Autowired private EmployeeRepository employeeRepository;
	@Autowired private DepartmentsRepository departmentsRepository;
	@Autowired KeyValueTemplate kvTemplate;
	
	//@Before
    public void setUp() throws JsonProcessingException {
		Address home = Address.builder().street("ABC Street").city("Pune").build();
		Address offc = Address.builder().street("XYZ Street").city("Pune").build();
		
		// Projects
		Project linkedIn = Project.builder().projectName("Linked-in").projectCategory("Social").build();
		Project stack = Project.builder().projectName("StackOverFlow").projectCategory("Developer-Social").build();
		Project electrical = Project.builder().projectName("Electrical").projectCategory("Electric").build();
		Project mechanical = Project.builder().projectName("Mechanical").projectCategory("Machines").build();
		
		Project ml = Project.builder().projectName("ML").projectCategory("Social").build();
		
		// Departments
		Department ai = Department.builder().departmentName("AI").projects(Arrays.asList(linkedIn, stack)).build();
		Department nonAI = Department.builder().departmentName("NON-AI").projects(Arrays.asList(electrical, mechanical)).build();
		Department dml = Department.builder().departmentName("ML").projects(Arrays.asList(ml)).build();
		
		// Employee Details
		Employee raj = Employee.builder().firstName("Raj").lastName("Kumar")
				.addresses(Arrays.asList(home, offc))
				.departments(Arrays.asList(ai, nonAI)).build();

		Employee john = Employee.builder().firstName("John").lastName("Doe")
				.addresses(Arrays.asList(home, offc))
				.departments(Arrays.asList(dml)).build();
		

		employeeRepository.save(raj);
		employeeRepository.save(john);
			
		List<Employee> employees = employeeRepository.findByAddresses_Street("XYZ Street");
		System.out.println("EMPLOYEE = "+employees);
	}
	
	@Test
	public void testEmployeeByExample() {
		//Example<Employee> example = Example.of(Employee.builder().firstName("Raj").build());
		Example<Employee> example = Example.of(new Employee(null, "Raj", null, null, null));
		long count = employeeRepository.count(example);
		System.out.println("COUNT_OF_EMPLOYEE = "+count);
		
	}
}
