package com.example.chayen.cookingsupporter.FoodListAdapter;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chayen on 25-Jan-17.
 */

public class FoodDatabaseClass implements Serializable{
    public String[] cooking_method;
    public String food_image;
    public String food_name;
    public String food_type;
    public String[] ingredient;

    public FoodDatabaseClass(){

    }

    public FoodDatabaseClass(String[] cooking_method, String food_image, String food_name, String food_type, String[] ingredient){
        this.cooking_method = cooking_method;
        this.food_image = food_image;
        this.food_name = food_name;
        this.food_type = food_type;
        this.ingredient = ingredient;
    }

    public String getFood_image(){
        return food_image;
    }

    public String getFood_name(){
        return food_name;
    }

    public String getFood_type(){
        return food_type;
    }

    public String[] getCooking_method(){
        return cooking_method;
    }

    public String[] getIngredient(){
        return ingredient;
    }

//    @Exclude
//    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("cooking_method", cooking_method);
//        result.put("food_image", food_image);
//        result.put("food_name", food_name);
//        result.put("food_type", food_type);
//        result.put("ingredient", ingredient);
//        return result;
//    }
}
