package com.example.chayen.cookingsupporter.FoodListAdapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    static int cooking_position;
    ViewHolder holder = new ViewHolder();
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_recipe);
        initInstance();
        renderRecipe();
    }

    public static void getFoodRecipePosition(int position){
        cooking_position = position;
    }

    static class ViewHolder {
        TextView cooking_recipe_name;
        TextView cooking_recipe_type;
        ArrayList<String> cooking_recipe_ingredient;
        ArrayList<String> cooking_recipe_method;
        ImageView cooking_recipe_image;
        ListView cooking_recipe_ingredientList;
        ListView cooking_recipe_methodList;
        ListAdapter cooking_recipe_ingredientAdapter;
        ListAdapter cooking_recipe_methodAdapter;
    }

    private void initInstance(){
        if(view == null){
            holder.cooking_recipe_image = (ImageView)findViewById(R.id.cooking_recipe_foodimage);
            holder.cooking_recipe_name = (TextView)findViewById(R.id.cooking_recipe_foodname);
            holder.cooking_recipe_type = (TextView)findViewById(R.id.cooking_recipe_foodtype);

            holder.cooking_recipe_ingredientAdapter = new ArrayAdapter<String>(this, R.layout.cooking_recipe_adapter,
                    foodlist.get(cooking_position).getIngredient());
            holder.cooking_recipe_methodAdapter = new ArrayAdapter<String>(this, R.layout.cooking_recipe_adapter,
                    foodlist.get(cooking_position).getCooking_method());

            holder.cooking_recipe_ingredientList = (ListView)findViewById(R.id.cooking_recipe_ingredient_list);
            holder.cooking_recipe_methodList = (ListView)findViewById(R.id.cooking_recipe_method_list);
//            view.setTag(holder);
        }else{
//            holder = (ViewHolder)view.getTag();
        }

    }

    private void renderRecipe(){
        Picasso.with(getApplicationContext()).load(foodlist.get(cooking_position).getFood_image()).into(holder.cooking_recipe_image);
        holder.cooking_recipe_name.setText(foodlist.get(cooking_position).getFood_name());
        holder.cooking_recipe_type.setText(foodlist.get(cooking_position).getFood_type());
        holder.cooking_recipe_ingredientList.setAdapter(holder.cooking_recipe_ingredientAdapter);
        holder.cooking_recipe_methodList.setAdapter(holder.cooking_recipe_methodAdapter);
    }
}
