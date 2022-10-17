package com.example.mongodb.springbootmongodb.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import static com.mongodb.client.model.Filters.*;
import com.example.mongodb.springbootmongodb.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

import com.example.mongodb.springbootmongodb.model.Menu;
import com.example.mongodb.springbootmongodb.repository.MenuRepository;
import com.example.mongodb.springbootmongodb.menurules.MenuRules;

@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuRepository MenuRepository;

	String uri = "mongo uri goes here, or in a conf file would be better";
	private MenuClient menuClient = new MenuClient(uri);
	private MenuRules menuRules = new MenuRules();

	private List<String> meals = Arrays.asList("Breakfast", "Lunch", "Dinner");

	@GetMapping("/{meal}/{order}")
	public ResponseEntity<List<String>> orderMeal(
			@PathVariable(value = "meal") String meal,
			@PathVariable(value = "order") String order
		) {
		String finalOrder = "";
		if (!meals.contains(meal))
			finalOrder = meal+" is not a meal, come on";
		else {
			Document d = menuClient.findSubMenu(meal);
			Document mealOptions = (Document)d.get(meal);
			finalOrder = menuRules.constructOrder(meal,order,mealOptions);
			System.out.println(finalOrder);
		}
		List<String> l = new ArrayList<String>();
		l.add(finalOrder);
		return ResponseEntity.ok(l);
	}

	
	@GetMapping("/")
	public ResponseEntity<String> getMenu() {
		Document m1 = menuClient.getFullMenu();//(Document)menuClient.collection.find();
		return ResponseEntity.ok(m1.toJson());
	}

}
