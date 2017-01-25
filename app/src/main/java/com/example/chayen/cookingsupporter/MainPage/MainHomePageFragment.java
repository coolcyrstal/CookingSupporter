package com.example.chayen.cookingsupporter.MainPage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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
    String[] food_name, food_star;
    List<FoodDatabaseClass> foodlist;

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
        foodlist_adapter = new FoodListAdapter();
        foodlist_adapter.setFood_name(food_name);
        foodlist_adapter.setStar_value(testfoodlist);
        listView.setAdapter(foodlist_adapter);
    }

    private void setFireBaseDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("food");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, FoodDatabaseClass> td = (HashMap<String,FoodDatabaseClass>) dataSnapshot.getValue();
                foodlist = new ArrayList<>(td.values());

                for(int i = 0; i < foodlist.size(); i++){
//                    food_name[i] = foodlist.get(i).food_name;
                    Log.d("foodlist", "" + foodlist.get(0));
                }
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
