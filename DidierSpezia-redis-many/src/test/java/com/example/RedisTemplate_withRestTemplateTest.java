package com.example;

import java.util.Set;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.Category;
import com.example.model.User;
import com.example.repository.CategoryRepository;
import com.example.repository.UserRepository;
import com.example.test.config.RepositoryTestSupport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplate_withRestTemplateTest extends RepositoryTestSupport {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;


	@Before
	public void beforeClass() throws JsonProcessingException {
		
		// Category Details
		Category c1 = Category.builder().id("c"+UUID.randomUUID().toString()).name("Cinema").build();
		Category c2 = Category.builder().id("c"+UUID.randomUUID().toString()).name("Sports").build();
		Category c3 = Category.builder().id("c"+UUID.randomUUID().toString()).name("Music").build();
		Category c4 = Category.builder().id("c"+UUID.randomUUID().toString()).name("Nature").build();
		
		redisTemplate.opsForValue().set("category:"+c1.getId(),  new ObjectMapper().writeValueAsString(c1));
		redisTemplate.opsForValue().set("category:"+c2.getId(),  new ObjectMapper().writeValueAsString(c2));
		redisTemplate.opsForValue().set("category:"+c3.getId(),  new ObjectMapper().writeValueAsString(c3));
		redisTemplate.opsForValue().set("category:"+c4.getId(),  new ObjectMapper().writeValueAsString(c4));
		
		
		// User 
		User u1 = User.builder().id("u"+UUID.randomUUID().toString()).firstName("Chris").emailId("chris.rogers@gmail.com").build(); //1
		User u2 = User.builder().id("u"+UUID.randomUUID().toString()).firstName("John").emailId("john.doe@gmail.com").build();  //2
		User u3 = User.builder().id("u"+UUID.randomUUID().toString()).firstName("Julia").emailId("julia.cox@gmail.com").build(); //3
		
		redisTemplate.opsForValue().set("user:"+u1.getId(), new ObjectMapper().writeValueAsString(u1));
		redisTemplate.opsForValue().set("user:"+u2.getId(), new ObjectMapper().writeValueAsString(u2));
		redisTemplate.opsForValue().set("user:"+u3.getId(), new ObjectMapper().writeValueAsString(u3));
		
		
		redisTemplate.opsForSet().add("category:"+c1.getId()+":users", u1.getId(),u3.getId());
		redisTemplate.opsForSet().add("category:"+c2.getId()+":users", u2.getId(), u3.getId());
		redisTemplate.opsForSet().add("category:"+c3.getId()+":users", u1.getId());
		redisTemplate.opsForSet().add("category:"+c4.getId()+":users", u2.getId(), u3.getId());
		
		
		redisTemplate.opsForSet().add("user:"+u1.getId()+":categories", c1.getId(),c3.getId());
		redisTemplate.opsForSet().add("user:"+u2.getId()+":categories", c2.getId(), c4.getId() );
		redisTemplate.opsForSet().add("user:"+u3.getId()+":categories", c1.getId(), c2.getId(), c4.getId());
		
		
		Set<String> members = redisTemplate.opsForSet().members("category:"+c1.getId()+":users");
		System.out.println("MEMBERS LIST = "+members);
		
		for (String string : members) {
			String user1 = redisTemplate.opsForValue().get("user:"+string);
			System.out.println("USER-1 ="+user1);
		}
	}

	@Test
	public void test() {
		/*Set<String> members = redisTemplate.opsForSet().members("category:023f7f78-ac4b-4897-90fc-816484e09b95:users");
		System.out.println("MEMBERS LIST = "+members);
		
		for (String string : members) {
			String user1 = redisTemplate.opsForValue().get("user:"+string);
			System.out.println("USER-1 ="+user1);
		}*/
	}
}
