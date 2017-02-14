package com.example.chayen.cookingsupporter.NavigationAndSearch.Category;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.R;

import java.util.ArrayList;

public class CategoryPage extends AppCompatActivity {

    private CategoryAdapter myAdapter;
    private RecyclerView recyclerView;
    private ArrayList<FoodDatabaseClass> category_foodlist = new ArrayList<>();

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

        myAdapter = new CategoryAdapter(category_foodlist);
        getData();
        recyclerView.setAdapter(myAdapter);
    }

    private void getData(){

    }

    @Override
    protected void onResume(){
        super.onResume();
        ((CategoryAdapter) myAdapter).setOnItemClickListener(new CategoryAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }
        });
    }
}
