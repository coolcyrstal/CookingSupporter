package com.example.chayen.cookingsupporter.NavigationAndSearch.Category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.R;

import java.util.ArrayList;
import java.util.Collection;

import static com.example.chayen.cookingsupporter.MainPage.MainHomePageFragment.foodlist;
import static com.example.chayen.cookingsupporter.NavigationAndSearch.Category.CategoryFoodPage.category_food;

public class CategoryPage extends AppCompatActivity {

    private CategoryAdapter myAdapter;
    private RecyclerView recyclerView;
    private ArrayList<FoodDatabaseClass> category_foodlist = new ArrayList<>();
    public static String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);

        initialize();
    }

    private void initialize(){
        recyclerView = (RecyclerView)findViewById(R.id.category_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        getData(category);
        myAdapter = new CategoryAdapter(category_foodlist);
        recyclerView.setAdapter(myAdapter);
    }

    private void getData(String category){
        for(int i = 0; i < foodlist.size(); i++){
//            Log.d("category filter", "" + foodlist.get(i).getFood_type() + "\n" + category);
            if(foodlist.get(i).getFood_type().equals(category)){
                category_foodlist.add(foodlist.get(i));
//                Log.d("category filter", "" + category_foodlist.get(0).getFood_type());
            }
        }
//        category_foodlist = foodlist;
//        Log.d("inheritfood test", ""+ category_foodlist.get(0).getFood_name());
    }

    @Override
    protected void onResume(){
        super.onResume();
        ((CategoryAdapter) myAdapter).setOnItemClickListener(new CategoryAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(CategoryPage.this, CategoryFoodPage.class);
                startActivity(intent);
                category_food = category_foodlist.get(position);
            }
        });
    }
}
