package com.example.chayen.cookingsupporter.NavigationAndSearch.Search;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.R;

import java.util.ArrayList;

import static com.example.chayen.cookingsupporter.MainPage.MainFoodPage.MainHomePageFragment.foodlist;


public class SearchPage extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView searchpage_recyclerview;
    private ArrayList<FoodDatabaseClass> searchpage_foodlist;
    private SearchPageAdapter searchPageAdapter;

    public SearchPage() {
        // Required empty public constructor
    }


    public static SearchPage newInstance() {
        SearchPage fragment = new SearchPage();
        Bundle args = new Bundle();
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
        searchpage_foodlist = foodlist;
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
