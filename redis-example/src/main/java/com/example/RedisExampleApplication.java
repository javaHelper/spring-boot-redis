package com.example;

import static org.springframework.data.domain.ExampleMatcher.matching;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import com.example.model.User;
import com.example.repository.UserRepository;

@SpringBootApplication
public class RedisExampleApplication implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(RedisExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();

		User skyler = userRepository.save(new User("Skyler", "Doe", 45));
		User walter = userRepository.save(new User("Karan", "White", 50));
		User flynn = userRepository.save(new User("Walter Jr. (Flynn)", "Kerr", 17));
		userRepository.saveAll(Arrays.asList(skyler, walter, flynn));
			
		long count = userRepository.count(Example.of(new User(null, "White", null)));
		System.out.println("COUNT ? "+count);
		
		Example<User> example1 = Example.of(new User("Walter Jr. (Flynn)", "Kerr", 17), matching().//
				withIgnorePaths("firstname", "lastname"));

		Optional<User> findOne = userRepository.findOne(example1);
		System.out.println(findOne);
	}
}
