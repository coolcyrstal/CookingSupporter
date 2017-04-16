package com.example.chayen.cookingsupporter.NavigationAndSearch.Category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.NavigationAndSearch.Category.CategoryFoodPage.CategoryFoodPage;
import com.example.chayen.cookingsupporter.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.chayen.cookingsupporter.NavigationAndSearch.Category.CategoryFoodPage.CategoryFoodPage.category_food;

public class CategoryPage extends AppCompatActivity {

    private CategoryAdapter myAdapter;
    private RecyclerView recyclerView;
    private ArrayList<FoodDatabaseClass> category_foodlist = new ArrayList<>();
    public static String category;
    FoodDatabaseClass food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);

        initialize();
    }

    private void initialize(){
        recyclerView = (RecyclerView)findViewById(R.id.category_recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        myAdapter = new CategoryAdapter(category_foodlist);
        getData(category);


    }

    private void getData(final String category){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("food");
        Query queryCategory = myRef.orderByChild("food_type").equalTo(category);
        queryCategory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot messageSnapshot: dataSnapshot.getChildren()){
                    String food_image = (String) messageSnapshot.child("food_image").getValue();
                    String food_name = (String) messageSnapshot.child("food_name").getValue();
                    String food_type = (String) messageSnapshot.child("food_type").getValue();
                    String author = (String) messageSnapshot.child("author").getValue();
                    ArrayList<String> cooking_method = (ArrayList<String>) messageSnapshot.child("cooking_method").getValue();
                    ArrayList<String> ingredient = (ArrayList<String>) messageSnapshot.child("ingredient").getValue();
                    Long star_count = (Long) messageSnapshot.child("star_count").getValue();
                    Long user_count = (Long) messageSnapshot.child("user_count").getValue();
                    food = new FoodDatabaseClass();
                    food.setAuthor(author);
                    food.setCooking_method(cooking_method);
                    food.setFood_image(food_image);
                    food.setFood_name(food_name);
                    food.setFood_type(food_type);
                    food.setIngredient(ingredient);
                    food.setStar_count(star_count);
                    food.setUser_count(user_count);
                    category_foodlist.add(food);
//                    Log.d("testcategoryfoodlist", "" + food.getFood_name());
                }
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        for(int i = 0; i < foodlist.size(); i++){
////            Log.d("category filter", "" + foodlist.get(i).getFood_type() + "\n" + category);
//            if(foodlist.get(i).getFood_type().equals(category)){
//                category_foodlist.add(foodlist.get(i));
////                Log.d("category filter", "" + category_foodlist.get(0).getFood_type());
//            }
//        }
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
