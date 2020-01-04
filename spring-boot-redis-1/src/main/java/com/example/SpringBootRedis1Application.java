package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.model.Item;
import com.example.repository.ItemRepository;

@SpringBootApplication
public class SpringBootRedis1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedis1Application.class, args);
	}
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public void run(String... args) throws Exception {
		Item item1 = Item.builder().name("Aaloo Bonda").category("Junk Food").build();
		Item item2 = Item.builder().name("Sambar Vadi").category("Junk Food").build();
		Item item3 = Item.builder().name("Peethala Bhakari").category("Good Food").build();
		
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
		
		itemRepository.saveAll(items);
	}

}
