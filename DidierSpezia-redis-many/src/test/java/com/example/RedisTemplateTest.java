package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.Category;
import com.example.model.User;
import com.example.test.config.RepositoryTestSupport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest extends RepositoryTestSupport {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;


	@Before
	public void beforeClass() throws JsonProcessingException {
		
		// Category Details
		Category c1 = Category.builder().id("c1").name("Cinema").build();
		Category c2 = Category.builder().id("c2").name("Sports").build();
		Category c3 = Category.builder().id("c3").name("Music").build();
		Category c4 = Category.builder().id("c4").name("Nature").build();
		
		redisTemplate.opsForSet().add("category:1", new ObjectMapper().writeValueAsString(c1));
		redisTemplate.opsForSet().add("category:2", new ObjectMapper().writeValueAsString(c2));
		redisTemplate.opsForSet().add("category:3", new ObjectMapper().writeValueAsString(c3));
		redisTemplate.opsForSet().add("category:4", new ObjectMapper().writeValueAsString(c4));
		
		// User 
		User u1 = User.builder().id("u1").firstName("Chris").emailId("chris.rogers@gmail.com").build(); //1
		User u2 = User.builder().id("u2").firstName("John").emailId("john.doe@gmail.com").build();  //2
		User u3 = User.builder().id("u3").firstName("Julia").emailId("julia.cox@gmail.com").build(); //3
		
		redisTemplate.opsForSet().add("user:1", new ObjectMapper().writeValueAsString(u1));
		redisTemplate.opsForSet().add("user:2", new ObjectMapper().writeValueAsString(u2));
		redisTemplate.opsForSet().add("user:3", new ObjectMapper().writeValueAsString(u3));

		
		redisTemplate.opsForSet().add("category:1:users", "1","3");
		redisTemplate.opsForSet().add("category:2:users", "2","3");
		redisTemplate.opsForSet().add("category:3:users", "1");
		redisTemplate.opsForSet().add("category:4:users", "2","3");
		
		redisTemplate.opsForSet().add("user:1:categories", "1","3");
		redisTemplate.opsForSet().add("user:2:categories", "2", "4" );
		redisTemplate.opsForSet().add("user:3:categories", "1", "2", "4");
		
		
		/*redisTemplate.opsForSet().add("category:1:users", new ObjectMapper().writeValueAsString(Arrays.asList(u1, u3)));
		redisTemplate.opsForSet().add("category:2:users", new ObjectMapper().writeValueAsString(Arrays.asList(u2, u3)));
		redisTemplate.opsForSet().add("category:3:users", new ObjectMapper().writeValueAsString(Arrays.asList(u1)));
		redisTemplate.opsForSet().add("category:4:users", new ObjectMapper().writeValueAsString(Arrays.asList(u2, u3)));
		
		
		redisTemplate.opsForSet().add("user:1:categories", new ObjectMapper().writeValueAsString(Arrays.asList(c1, c3)));
		redisTemplate.opsForSet().add("user:2:categories", new ObjectMapper().writeValueAsString(Arrays.asList(c1, c3)));
		redisTemplate.opsForSet().add("user:3:categories", new ObjectMapper().writeValueAsString(Arrays.asList(c1, c2, c4)));*/
		
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
	}

}
