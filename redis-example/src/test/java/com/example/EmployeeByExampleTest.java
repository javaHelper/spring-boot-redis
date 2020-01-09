package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.mastercard.config.RepositoryTestSupport;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.data.domain.ExampleMatcher.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeByExampleTest extends RepositoryTestSupport {
	@Autowired
	UserRepository repository;

	User skyler, walter, flynn, marie, hank;

	@Before
	public void setUp() {

		repository.deleteAll();

		this.skyler = repository.save(new User("Skyler", "White", 45));
		this.walter = repository.save(new User("Walter", "White", 50));
		this.flynn = repository.save(new User("Walter Jr. (Flynn)", "White", 17));
		this.marie = repository.save(new User("Marie", "Schrader", 38));
		this.hank = repository.save(new User("Hank", "Schrader", 43));
	}

	@Test
	public void countByExample() {
		assertThat(repository.count(Example.of(new User(null, "White", null))), is(3L));
	}

	@Test
	public void countSubtypesByExample() {
		assertThat(repository.count(Example.of(new User(null, "Schrader", null))), is(2L));
	}
	
	@Test
	public void ignorePropertiesAndMatchByAge() {

		Example<User> example = Example.of(flynn, matching().//
				withIgnorePaths("firstname", "lastname"));

		Iterable<User> findAll = repository.findAll(example);
		for (User user : findAll) {
			System.out.println("USER ? "+user);
		}
		
	}
	
	/*@Test
	public void substringMatching() {

		Example<User> example = Example.of(new User("er", null, null), matching().//
				withStringMatcher(StringMatcher.ENDING));

		assertThat(repository.findAll(example), hasItems(skyler, walter));
	}*/
}
