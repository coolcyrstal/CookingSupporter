package com.example.chayen.cookingsupporter.NavigationAndSearch.Category;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.R;
import com.squareup.picasso.Picasso;

import static com.example.chayen.cookingsupporter.NavigationAndSearch.Category.CategoryPage.category_foodlist;

public class CategoryFoodPage extends AppCompatActivity {

    ImageView category_foodpage_image;
    TextView category_foodpage_name, category_foodpage_type;
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

        Picasso.with(getApplicationContext()).load(category_food.getFood_image()).into(category_foodpage_image);
        category_foodpage_name.setText(category_food.getFood_name());
        category_foodpage_type.setText(category_food.getFood_type());
    }
}
