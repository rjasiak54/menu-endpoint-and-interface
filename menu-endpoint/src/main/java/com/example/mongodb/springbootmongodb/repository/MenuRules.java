package com.example.mongodb.springbootmongodb.menurules;

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
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.example.mongodb.springbootmongodb.model.Menu;
import com.example.mongodb.springbootmongodb.repository.MenuRepository;
import com.example.mongodb.springbootmongodb.menurules.MenuRules;
import java.util.Map;
import java.util.HashMap;

public class MenuRules {

    public Map<Character,String> indexToCourse = new HashMap<>();

    public MenuRules() {
        indexToCourse.put('1',"Main"); 
        indexToCourse.put('2',"Side"); 
        indexToCourse.put('3',"Drink"); 
        indexToCourse.put('4',"Desert"); 

    }

    public String constructOrder(String meal, String order, Document menuMap) {
        // return "";
        Map<Character,Integer> orderMap = new HashMap<>();
        for (char c: order.toCharArray()) {
            if (orderMap.containsKey(c)) {
                if (multipleRuleBroken(meal,c)) return "Error: cannot order multiple "+menuMap.get(indexToCourse.get(c));
                orderMap.put(c,orderMap.get(c)+1);
            }
            else
                orderMap.put(c,1);
        }

        //
        // Rules:
        if (!orderMap.containsKey('1')) return "Main is missing";
        if (!orderMap.containsKey('2')) return "Side is missing";
        if (meal.equals("Dinner") && !orderMap.containsKey('4')) return "Desert is missing"; 

        String finalOrder = makeCourse(meal,'1',menuMap,orderMap);
        finalOrder += ", "+makeCourse(meal,'2',menuMap,orderMap);

        if (orderMap.containsKey('3')) finalOrder += ", "+makeCourse(meal,'3',menuMap,orderMap);
        else if (!meal.equals("Dinner")) finalOrder += ", Water";
        
        if (orderMap.containsKey('4')) finalOrder += ", Water, Cake";

        return finalOrder;
        
    }

    boolean multipleRuleBroken(String meal, Character item) {
        if (meal.equals("Breakfast") && item == '3') return false;
        if (meal.equals("Lunch") && item == '2') return false;
        return true;
    }

    String makeCourse(String meal, Character item, Document menuMap, Map<Character,Integer> orderMap) {
        if (orderMap.get(item) > 1) return menuMap.get(indexToCourse.get(item))+"("+orderMap.get(item)+")";
        return (String)menuMap.get(indexToCourse.get(item));
    }


}