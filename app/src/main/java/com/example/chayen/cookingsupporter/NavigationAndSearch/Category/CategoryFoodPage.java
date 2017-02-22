package com.example.chayen.cookingsupporter.NavigationAndSearch.Category;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.R;
import com.squareup.picasso.Picasso;

public class CategoryFoodPage extends AppCompatActivity {

    ImageView category_foodpage_image;
    TextView category_foodpage_name, category_foodpage_type;
    RecyclerView category_foodpage_ingredient, category_foodpage_cookingmethod;
    CategoryFoodPageAdapter categoryFoodPageAdapter_ingredient, categoryFoodPageAdapter_cookingmethod;

    public static FoodDatabaseClass category_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_food_page);

        initialize();
    }

    private void initialize(){
        category_foodpage_image = (ImageView)findViewById(R.id.category_cooking_recipe_foodimage);
        category_foodpage_name = (TextView)findViewById(R.id.category_cooking_recipe_foodname);
        category_foodpage_type = (TextView)findViewById(R.id.category_cooking_recipe_foodtype);
        category_foodpage_ingredient = (RecyclerView)findViewById(R.id.category_cooking_recipe_ingredient_list);
        category_foodpage_cookingmethod = (RecyclerView)findViewById(R.id.category_cooking_recipe_method_list);

        Picasso.with(getApplicationContext()).load(category_food.getFood_image()).into(category_foodpage_image);
        category_foodpage_name.setText(category_food.getFood_name());
        category_foodpage_type.setText(category_food.getFood_type());

        categoryFoodPageAdapter_ingredient = new CategoryFoodPageAdapter(category_food.getIngredient());
        category_foodpage_ingredient.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        category_foodpage_ingredient.setAdapter(categoryFoodPageAdapter_ingredient);
//        Log.d("inheritfood test", ""+ category_food.getIngredient());
        categoryFoodPageAdapter_cookingmethod = new CategoryFoodPageAdapter(category_food.getCooking_method());
        category_foodpage_cookingmethod.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        category_foodpage_cookingmethod.setAdapter(categoryFoodPageAdapter_cookingmethod);
    }

    @Override
    protected void onResume(){
        super.onResume();
        ((CategoryFoodPageAdapter) categoryFoodPageAdapter_ingredient).setOnItemClickListener(new CategoryFoodPageAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
            }
        });
        ((CategoryFoodPageAdapter) categoryFoodPageAdapter_cookingmethod).setOnItemClickListener(new CategoryFoodPageAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
            }
        });
    }
}
