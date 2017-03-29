package com.example.chayen.cookingsupporter.NavigationAndSearch.Search;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.chayen.cookingsupporter.MainPage.MainFoodPage.MainHomePageFragment.foodlist;
import static com.example.chayen.cookingsupporter.NavigationAndSearch.Search.Search.searchpage_button;


public class SearchPage extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView searchpage_recyclerview;
    private ArrayList<FoodDatabaseClass> searchpage_foodlist = new ArrayList<FoodDatabaseClass>();
    private FoodDatabaseClass food;
    private SearchPageAdapter searchPageAdapter;
    private static String searchtext_foodname;
    private static String searchtext_foodingredient;

    public SearchPage() {
        // Required empty public constructor
    }


    public static SearchPage newInstance(String text1, String text2) {
        SearchPage fragment = new SearchPage();
        Bundle args = new Bundle();
        searchtext_foodname = text1;
        searchtext_foodingredient = text2;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSearchData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_search_page, container, false);
        rootview.setFocusableInTouchMode(true);
        rootview.requestFocus();
        rootview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK){
                    getActivity().getSupportFragmentManager().popBackStack();
                    searchpage_button.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }
        });
        initialize(rootview);
        return rootview;
    }

    private void initialize(View rootview){
        searchpage_recyclerview = (RecyclerView)rootview.findViewById(R.id.searchpage_recyclerview);
        searchpage_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        searchPageAdapter = new SearchPageAdapter(searchpage_foodlist);
        searchpage_recyclerview.setAdapter(searchPageAdapter);
    }

    private void getSearchData(){
//        searchpage_foodlist = foodlist;

        ArrayList<FoodDatabaseClass> searchpage_foodlist_clone = new ArrayList<>();
        if(!searchtext_foodname.equals("")){
            for(FoodDatabaseClass food : foodlist){
                if(food.getFood_name().contains(searchtext_foodname)){
                    searchpage_foodlist.add(food);
                }
            }
            if(!searchtext_foodingredient.equals("")){
                for(FoodDatabaseClass food : searchpage_foodlist){
                    for(String ingredient : food.getIngredient()){
                        if(ingredient.contains(searchtext_foodingredient)){
                            searchpage_foodlist_clone.add(food);
                            break;
                        }
                    }
                }
                searchpage_foodlist = searchpage_foodlist_clone;
            }
        } else if(!searchtext_foodingredient.equals("")){
            for(FoodDatabaseClass food : foodlist){
                for(String ingredient : food.getIngredient()){
                    if(ingredient.contains(searchtext_foodingredient)){
                        searchpage_foodlist.add(food);
                        break;
                    }
                }
            }
        }
        else{
            searchpage_foodlist = foodlist;
        }

//        Log.d("searchcontain", "" + food.getFood_name());
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
