package com.example.mongodb.springbootmongodb.controller;

import java.util.List;
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
import static com.mongodb.client.model.Projections.*;

import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

class MenuClient {

    public MongoClient mongoClient;
    MongoDatabase database;
    public MongoCollection<Document> collection;
            
    
    public MenuClient(String uri) {
        mongoClient = MongoClients.create(uri);
        database = mongoClient.getDatabase("menu");
        collection = database.getCollection("menu");
    }

    public Document findSubMenu(String meal) {
        Bson projection = include(meal);
        FindIterable<Document> docs = collection.find().projection(projection);
        Document d = null;
        for (Document doc : docs) {
            d = doc;
            break;
        }
        System.out.println(d);
        return d;
    }

    public Document getFullMenu() {
        FindIterable<Document> docs = collection.find();
        Document d = null;
        for (Document doc : docs) {
            d = doc;
            break;
        }
        return d;
    }
}