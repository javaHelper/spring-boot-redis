package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Item;

public interface ItemRepository extends CrudRepository<Item, Integer>{

}
