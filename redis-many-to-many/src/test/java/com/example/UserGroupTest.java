package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.model.Group;
import com.example.model.User;
import com.example.model.UserGroupRel;
import com.example.repository.GroupRepository;
import com.example.repository.UserGroupRelRepository;
import com.example.repository.UserRepository;
import com.example.test.config.RepositoryTestSupport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserGroupTest extends RepositoryTestSupport{
	@Autowired private UserRepository userRepository;
	@Autowired private GroupRepository groupRepository;
	@Autowired private UserGroupRelRepository userGroupRelRepository;
	
	@Before
    public void setUp() throws JsonProcessingException {
		User raj = User.builder().name("Raj Kumar").createdDate(null).salary(new BigDecimal("0")).build();
		User parag = User.builder().name("Parag Rane").createdDate(null).salary(new BigDecimal("0")).build();
		User sagar = User.builder().name("Sagar Parate").createdDate(null).salary(new BigDecimal("0")).build();
		
		userRepository.saveAll(Arrays.asList(raj, parag, sagar));
		
		/*Group hardware = Group.builder().name("Hardware").build();
		Group software = Group.builder().name("Hardware").build();
		Group machineLearning = Group.builder().name("Mchine Learning").build();
		Group ai = Group.builder().name("Artificial Inteligence").build();
		
		groupRepository.saveAll(Arrays.asList(hardware, software, machineLearning));
		
		
		// Raj interested in Hardware and AI
		UserGroupRel userGroupRel1 = UserGroupRel.builder().userId(raj.getUserId()).groupId(hardware.getGroupId()).build();
		UserGroupRel userGroupRel2 = UserGroupRel.builder().userId(raj.getUserId()).groupId(ai.getGroupId()).build();
		
		// Parag interested in hardware, software and ML
		UserGroupRel userGroupRel_1 = UserGroupRel.builder().userId(parag.getUserId()).groupId(hardware.getGroupId()).build();
		UserGroupRel userGroupRel_2 = UserGroupRel.builder().userId(parag.getUserId()).groupId(software.getGroupId()).build();
		UserGroupRel userGroupRel_3 = UserGroupRel.builder().userId(parag.getUserId()).groupId(machineLearning.getGroupId()).build();
		
		// Sagar intersted in AI and ML
		UserGroupRel user_GroupRel1 = UserGroupRel.builder().userId(sagar.getUserId()).groupId(machineLearning.getGroupId()).build();
		UserGroupRel user_GroupRel2 = UserGroupRel.builder().userId(sagar.getUserId()).groupId(ai.getGroupId()).build();
		
		userGroupRelRepository.saveAll(Arrays.asList(userGroupRel1, userGroupRel2, userGroupRel_1, userGroupRel_2, 
				userGroupRel_3, user_GroupRel1, user_GroupRel2));
		
		
		List<UserGroupRel> userGroupRels = userGroupRelRepository.findByGroupId(hardware.getGroupId());
		System.out.println("USER GROUPS DETAILS SIZE :"+userGroupRels.size());
		for (UserGroupRel userGroupRel : userGroupRels) {
			System.out.println("USER_GROUP :"+new ObjectMapper().writeValueAsString(userGroupRel));
			List<User> users = userRepository.getByUserId(userGroupRel.getUserId());
			for(User u : users) {
				System.out.println("Users interested in Hardwares are : "+u.getName());
			}
		}*/
	}
	
	@Test
	public void test() {
		
	}

}
