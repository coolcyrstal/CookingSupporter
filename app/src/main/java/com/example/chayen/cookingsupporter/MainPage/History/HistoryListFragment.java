package com.example.chayen.cookingsupporter.MainPage.History;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.chayen.cookingsupporter.MainPage.MainHomePageFragment.foodlist;


public class HistoryListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    RecyclerView history_recyclerview;
    ArrayList<FoodDatabaseClass> history_foodlist;
    String checkUser;
    HistoryAdapter historyAdapter;
    FoodDatabaseClass food;

    public HistoryListFragment() {
        // Required empty public constructor
    }


    public static HistoryListFragment newInstance() {
        HistoryListFragment fragment = new HistoryListFragment();
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
        View rootview = inflater.inflate(R.layout.fragment_history_list, container, false);
        initialize(rootview);
        return rootview;
    }

    private void initialize(View rootview){
        history_recyclerview = (RecyclerView)rootview.findViewById(R.id.historyList);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            checkUser = user.getUid();
        }
        history_foodlist = new ArrayList<>();
        setHistoryFood();
        historyAdapter = new HistoryAdapter(history_foodlist);
        history_recyclerview.setAdapter(historyAdapter);
    }

    private void setHistoryFood(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("food");
        Query queryHistory = myRef.orderByChild("author").equalTo(checkUser);
        queryHistory.addValueEventListener(new ValueEventListener() {
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
                    food = new FoodDatabaseClass();
                    food.setAuthor(author);
                    food.setCooking_method(cooking_method);
                    food.setFood_image(food_image);
                    food.setFood_name(food_name);
                    food.setFood_type(food_type);
                    food.setIngredient(ingredient);
                    food.setStar_count(star_count);
                    history_foodlist.add(food);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
