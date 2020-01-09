package com.example;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.model.Address;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.example.test.config.RepositoryTestSupport;
import com.fasterxml.jackson.core.JsonProcessingException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeAdressTest extends RepositoryTestSupport{
	@Autowired private EmployeeRepository employeeRepository;
	
	@Before
    public void setUp() throws JsonProcessingException {
		Address home = Address.builder().street("ABC Street").city("Pune").build();
		Address offc = Address.builder().street("XYZ Street").city("Pune").build();
		
		Employee employee1 = Employee.builder().firstName("Raj").lastName("Kumar").addresses(Arrays.asList(home, offc)).build();
		employeeRepository.save(employee1);
			
		List<Employee> employees = employeeRepository.findByAddresses_Street("XYZ Street");
		System.out.println("EMPLOYEE = "+employees);
	}
	
	@Test
	public void test() {
		
	}

}
