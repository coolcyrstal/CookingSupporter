package com.example.chayen.cookingsupporter.FoodListAdapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.chayen.cookingsupporter.MainPage.MainHomePageFragment.foodlist;

public class CookingRecipe extends AppCompatActivity {

    ImageView cooking_recipe_image;
    TextView cooking_recipe_name, cooking_recipe_type;
    ArrayList<String> cooking_recipe_ingredient, cooking_recipe_method;
    ListAdapter cooking_recipe_ingredientAdapter, cooking_recipe_methodAdapter;
    ListView cooking_recipe_ingredientList, cooking_recipe_methodList;
    static int cooking_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_recipe);
        initInstance();
    }

    public static void getFoodRecipePosition(int position){
        cooking_position = position;
    }

    private void initInstance(){
        cooking_recipe_image = (ImageView)findViewById(R.id.cooking_recipe_foodimage);
        cooking_recipe_name = (TextView)findViewById(R.id.cooking_recipe_foodname);
        cooking_recipe_type = (TextView)findViewById(R.id.cooking_recipe_foodtype);

        cooking_recipe_ingredientAdapter = new ArrayAdapter<String>(this, R.layout.cooking_recipe_adapter,
                foodlist.get(cooking_position).getIngredient());
        cooking_recipe_methodAdapter = new ArrayAdapter<String>(this, R.layout.cooking_recipe_adapter,
                foodlist.get(cooking_position).getCooking_method());

        cooking_recipe_ingredientList = (ListView)findViewById(R.id.cooking_recipe_ingredient_list);
        cooking_recipe_methodList = (ListView)findViewById(R.id.cooking_recipe_method_list);

        renderRecipe();
    }

    private void renderRecipe(){
        Picasso.with(getApplicationContext()).load(foodlist.get(cooking_position).getFood_image()).into(cooking_recipe_image);
        cooking_recipe_name.setText(foodlist.get(cooking_position).getFood_name());
        cooking_recipe_type.setText(foodlist.get(cooking_position).getFood_type());
        cooking_recipe_ingredientList.setAdapter(cooking_recipe_ingredientAdapter);
        cooking_recipe_methodList.setAdapter(cooking_recipe_methodAdapter);
    }
}
