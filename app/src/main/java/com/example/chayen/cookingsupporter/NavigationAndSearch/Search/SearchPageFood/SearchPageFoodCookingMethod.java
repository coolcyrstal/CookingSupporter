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


public class SearchPageFoodCookingMethod extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView searchpage_cookingmethod;
    private SearchPageFoodAdapter searchPageFoodAdapter;
    private static ArrayList<String> searchpage_cookingmethod_list;

    public SearchPageFoodCookingMethod() {
        // Required empty public constructor
    }


    public static SearchPageFoodCookingMethod newInstance(ArrayList<String> myDataset) {
        SearchPageFoodCookingMethod fragment = new SearchPageFoodCookingMethod();
        Bundle args = new Bundle();
        searchpage_cookingmethod_list = myDataset;
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
        View rootview = inflater.inflate(R.layout.fragment_search_page_food_cooking_method, container, false);
        initialize(rootview);
        return rootview;
    }

    private void initialize(View rootview){
        searchpage_cookingmethod = (RecyclerView)rootview.findViewById(R.id.searchpage_cooking_recipe_method_list);
        searchPageFoodAdapter = new SearchPageFoodAdapter(searchpage_cookingmethod_list);
        searchpage_cookingmethod.setLayoutManager(new LinearLayoutManager(getContext()));
        searchpage_cookingmethod.setAdapter(searchPageFoodAdapter);
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
