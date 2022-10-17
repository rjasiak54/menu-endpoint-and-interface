package com.example.mongodb.springbootmongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "menu")
public class Menu {
	
	// @Id
	// private int id;
	private String name;



	// public int getId() {
	// 	return id;
	// }
	// public void setId(int id) {
	// 	this.id = id;
	// }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	

}
