package com.example.mongodb.springbootmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mongodb.springbootmongodb.model.Menu;

public interface MenuRepository extends MongoRepository<Menu, Integer>{
    
    
}
