package com.example.chayen.cookingsupporter.NavigationAndSearch.Search.SearchPageFood;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chayen.cookingsupporter.R;

import java.util.ArrayList;


public class SearchPageFoodIngredient extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView searchpage_ingredient;
    private SearchPageFoodAdapter searchPageFoodAdapter;
    private static ArrayList<String> searchpage_ingredient_list;

    public SearchPageFoodIngredient() {
        // Required empty public constructor
    }


    public static SearchPageFoodIngredient newInstance(ArrayList<String> myDataset) {
        SearchPageFoodIngredient fragment = new SearchPageFoodIngredient();
        Bundle args = new Bundle();
        searchpage_ingredient_list = myDataset;
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
        View rootview = inflater.inflate(R.layout.fragment_search_page_food_ingredient, container, false);
        initialize(rootview);
        return rootview;
    }

    private void initialize(View rootview){
        searchpage_ingredient = (RecyclerView)rootview.findViewById(R.id.searchpage_cooking_recipe_ingredient_list);
        searchPageFoodAdapter = new SearchPageFoodAdapter(searchpage_ingredient_list);
        searchpage_ingredient.setLayoutManager(new LinearLayoutManager(getContext()));
        searchpage_ingredient.setAdapter(searchPageFoodAdapter);
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
