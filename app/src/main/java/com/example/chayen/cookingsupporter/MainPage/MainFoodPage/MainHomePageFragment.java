package com.example.chayen.cookingsupporter.MainPage.MainFoodPage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chayen.cookingsupporter.FoodListAdapter.CookingRecipe;
import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.FoodListAdapter.FoodListAdapter;
import com.example.chayen.cookingsupporter.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainHomePageFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    ListView listView;
    RecyclerView mainhomepageRecyclerview;
    FoodListAdapter foodlist_adapter;
    MainHomePageAdapter mainHomePageAdapter;
    String[] food_name, food_type, food_image;
    public static ArrayList<FoodDatabaseClass> foodlist;
    FoodDatabaseClass food;

    public MainHomePageFragment() {
        // Required empty public constructor
    }

    public static MainHomePageFragment newInstance() {
        MainHomePageFragment fragment = new MainHomePageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_main_home_page, container, false);

        initInstance(rootview);
        return rootview;
    }

    private void initInstance(View rootview){
//        String[] testfoodlist = new String[]{"1","2","3"};
        listView = (ListView)rootview.findViewById(R.id.foodList);
        foodlist_adapter = new FoodListAdapter();
//        mainhomepageRecyclerview = (RecyclerView)rootview.findViewById(R.id.foodList);
        setFireBaseDatabase();
    }

    private void setFireBaseDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("food");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                foodlist = new ArrayList<>();
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    String food_image = (String) messageSnapshot.child("food_image").getValue();
                    String food_name = (String) messageSnapshot.child("food_name").getValue();
                    String food_type = (String) messageSnapshot.child("food_type").getValue();
                    String author = (String) messageSnapshot.child("author").getValue();
                    ArrayList<String> cooking_method = (ArrayList<String>) messageSnapshot.child("cooking_method").getValue();
                    ArrayList<String> ingredient = (ArrayList<String>) messageSnapshot.child("ingredient").getValue();
                    Long star_count = (Long) messageSnapshot.child("star_count").getValue();
                    food = new FoodDatabaseClass();
                    food.setAuthor(author);
                    food.setCooking_method(cooking_method);
                    food.setFood_image(food_image);
                    food.setFood_name(food_name);
                    food.setFood_type(food_type);
                    food.setIngredient(ingredient);
                    food.setStar_count(star_count);
//                    Log.d("foodlist", "" + food.getFood_name());
//                    Log.d("foodlist", "" + foodlist.get(0).getFood_name());
                    foodlist.add(food);
                }
//                Log.d("foodlist", "" + foodlist.get(0).getFood_type());

                food_name = new String[foodlist.size()];
                food_type = new String[foodlist.size()];
                food_image = new String[foodlist.size()];
                for(int i = 0; i < foodlist.size(); i++){
                    food_name[i] = foodlist.get(i).getFood_name();
                    food_type[i] = foodlist.get(i).getFood_type();
                    food_image[i] = foodlist.get(i).getFood_image();
//                    Log.d("foodlist", "" + food_name[i]);
                }

                setFoodlist_adapterer();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setFoodlist_adapterer(){
//        food_name = new String[]{"", ""};
        foodlist_adapter.setFood_name(food_name);
        foodlist_adapter.setFood_type(food_type);
        foodlist_adapter.setFood_image(food_image);
        listView.setAdapter(foodlist_adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CookingRecipe.getFoodRecipePosition(position);
                Intent intent = new Intent(getActivity(), CookingRecipe.class);
                startActivity(intent);
            }
        });
//        mainHomePageAdapter = new MainHomePageAdapter(foodlist);
//        mainhomepageRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mainhomepageRecyclerview.setAdapter(mainHomePageAdapter);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
