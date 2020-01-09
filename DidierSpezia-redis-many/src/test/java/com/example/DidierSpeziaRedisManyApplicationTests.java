package com.example;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.Category;
import com.example.model.User;
import com.example.model.UserCategoryRel;
import com.example.repository.CategoryRepository;
import com.example.repository.UserCategoryRelRepository;
import com.example.repository.UserRepository;
import com.example.test.config.RepositoryTestSupport;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DidierSpeziaRedisManyApplicationTests extends RepositoryTestSupport{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UserCategoryRelRepository userCategoryRelRepository;

	@Before
	public void beforeClass() {
		// Create Users
		User u1 = User.builder().firstName("Jack").emailId("jack.rogers@gmail.com").build(); //1
		User u2 = User.builder().firstName("John").emailId("john.doe@gmail.com").build();  //2
		User u3 = User.builder().firstName("Julia").emailId("julia.cox@gmail.com").build(); //3

		userRepository.saveAll(Arrays.asList(u1, u2, u3));

		// Create Categories
		Category c1 = Category.builder().name("Cinema").build();
		Category c2 = Category.builder().name("Sports").build();
		Category c3 = Category.builder().name("Music").build();
		Category c4 = Category.builder().name("Nature").build();

		categoryRepository.saveAll(Arrays.asList(c1, c2, c3, c4));

		// # For each category, we keep a set of reference on the users
		//	# Jack likes cinema and sports
		//	# John likes music and nature
		//	# Julia likes cinema, music and nature
		
		//Jack
		UserCategoryRel jackcategoryOfUsers1 = UserCategoryRel.builder().userId(u1.getId()).categoryId(c1.getId()).build(); // Cat: Cinema
		UserCategoryRel jackcategoryOfUsers2 = UserCategoryRel.builder().userId(u1.getId()).categoryId(c2.getId()).build(); // Cat : Sports

		// John
		UserCategoryRel johncategoryOfUsers1 = UserCategoryRel.builder().userId(u2.getId()).categoryId(c3.getId()).build(); // Music
		UserCategoryRel johncategoryOfUsers2 = UserCategoryRel.builder().userId(u2.getId()).categoryId(c4.getId()).build(); // Nature
		
		// Julia
		UserCategoryRel juliacategoryOfUsers1 = UserCategoryRel.builder().userId(u3.getId()).categoryId(c1.getId()).build(); // Cinema
		UserCategoryRel juliacategoryOfUsers2 = UserCategoryRel.builder().userId(u3.getId()).categoryId(c3.getId()).build(); // Music
		UserCategoryRel juliacategoryOfUsers3 = UserCategoryRel.builder().userId(u3.getId()).categoryId(c4.getId()).build(); // Nature
		
		userCategoryRelRepository.saveAll(Arrays.asList(jackcategoryOfUsers1, jackcategoryOfUsers2, johncategoryOfUsers1, johncategoryOfUsers2, 
				juliacategoryOfUsers1, juliacategoryOfUsers2, juliacategoryOfUsers3));
		
		List<UserCategoryRel> userCategoryRels = userCategoryRelRepository.getByUserId(u1.getId());
		System.out.println("USERS CATEGORY LIST = "+userCategoryRels.size());
		
		for (UserCategoryRel userCategoryRel : userCategoryRels) {
			System.out.println("DETAILS FOUND ? "+userCategoryRel);
			Category category1 = categoryRepository.getCategoryById(userCategoryRel.getCategoryId());
			System.out.println("CATEGORIES NAME = "+category1.getName());
		}
		
		List<String> userIds = userRepository.getIdByFirstName("Jack");
		System.out.println("======================"+userIds);
	}

	@Test
	public void contextLoads() {
	}

}
