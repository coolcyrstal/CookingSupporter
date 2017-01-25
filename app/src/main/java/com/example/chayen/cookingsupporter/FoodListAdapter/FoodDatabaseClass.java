package com.example.chayen.cookingsupporter.FoodListAdapter;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chayen on 25-Jan-17.
 */

public class FoodDatabaseClass implements Serializable{
    public ArrayList<String> cooking_method;
    public String food_image;
    public String food_name;
    public String food_type;
    public ArrayList<String> ingredient;

    public FoodDatabaseClass(){

    }

    public FoodDatabaseClass(ArrayList<String> cooking_method, String food_image, String food_name, String food_type, ArrayList<String> ingredient){
        this.cooking_method = cooking_method;
        this.food_image = food_image;
        this.food_name = food_name;
        this.food_type = food_type;
        this.ingredient = ingredient;
    }

    public String getFood_image(){
        return food_image;
    }

    public void setFood_image(String food_image){
        this.food_image = food_image;
    }

    public String getFood_name(){
        return food_name;
    }

    public void setFood_name(String food_name){
        this.food_name = food_name;
    }

    public String getFood_type(){
        return food_type;
    }

    public void setFood_type(String food_type){
        this.food_type = food_type;
    }

    public ArrayList<String> getCooking_method(){
        return cooking_method;
    }

    public void setCooking_method(ArrayList<String> cooking_method){
        this.cooking_method = cooking_method;
    }

    public ArrayList<String> getIngredient(){
        return ingredient;
    }

    public void setIngredient(ArrayList<String> ingredient){
        this.ingredient = ingredient;
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
