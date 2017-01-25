package com.example.chayen.cookingsupporter.MainPage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.FoodListAdapter.FoodListAdapter;
import com.example.chayen.cookingsupporter.FoodListAdapter.FoodListView;
import com.example.chayen.cookingsupporter.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;


public class MainHomePageFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    ListView listView;
    FoodListAdapter foodlist_adapter;
    String[] food_name, food_type, food_image;
    ArrayList<FoodDatabaseClass> foodlist;
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
        String[] testfoodlist = new String[]{"1","2","3"};
        listView = (ListView)rootview.findViewById(R.id.foodList);
        setFireBaseDatabase();


    }

    private void setFireBaseDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("food");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Map<String, FoodDatabaseClass> td = (HashMap<String,FoodDatabaseClass>) dataSnapshot.getValue();
//                foodlist = new ArrayList<>(td.values());

//                Map<String, FoodDatabaseClass> td = (HashMap<String,FoodDatabaseClass>) dataSnapshot.getValue();
//                for (DataSnapshot jobSnapshot: dataSnapshot.getChildren()) {
//                    FoodDatabaseClass job = jobSnapshot.getValue(FoodDatabaseClass.class);
//                    td.put(jobSnapshot.getKey(), job);
//                }
//                foodlist = new ArrayList<>(td.values());
//                List<String> keys = new ArrayList<String>(td.keySet());
//
//                for (FoodDatabaseClass job: foodlist) {
//                    Log.d("firebase", job.getFood_name());
//                }

                foodlist = new ArrayList<>();
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    String food_image = (String) messageSnapshot.child("food_image").getValue();
                    String food_name = (String) messageSnapshot.child("food_name").getValue();
                    String food_type = (String) messageSnapshot.child("food_type").getValue();
                    ArrayList<String> cooking_method = (ArrayList<String>) messageSnapshot.child("cooking_method").getValue();
                    ArrayList<String> ingredient = (ArrayList<String>) messageSnapshot.child("ingredient").getValue();
                    food = new FoodDatabaseClass();
                    food.setCooking_method(cooking_method);
                    food.setFood_image(food_image);
                    food.setFood_name(food_name);
                    food.setFood_type(food_type);
                    food.setIngredient(ingredient);
                    Log.d("foodlist", "" + food.getFood_name());
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
                    Log.d("foodlist", "" + food_name[i]);
                }
                foodlist_adapter = new FoodListAdapter();
                foodlist_adapter.setFood_name(food_name);
                foodlist_adapter.setFood_type(food_type);
                foodlist_adapter.setFood_image(food_image);
                listView.setAdapter(foodlist_adapter);

//                for (Object obj : td.values()) {
//                    if (obj instanceof Map) {
//                        Map<String, Object> mapObj = (Map<String, Object>) obj;
//                        FoodDatabaseClass match = new FoodDatabaseClass();
//                        match.setCooking_method(mapObj.get());
//                        match.getFood_type();
//                        match.getCooking_method();
//                        match.getFood_image();
//                        match.getIngredient();
//                        foodlist.add(match);
//                    }
//                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        myRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                FoodDatabaseClass food = new FoodDatabaseClass();
//                food = dataSnapshot.getValue(FoodDatabaseClass.class);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
