package com.example.chayen.cookingsupporter.MainPage.History.HistoryListFoodPage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chayen.cookingsupporter.R;

import static com.example.chayen.cookingsupporter.MainPage.History.HistoryListFragment.history_foodpage_food;


public class HistoryFoodPageCookingMethod extends Fragment {

    private OnFragmentInteractionListener mListener;
    RecyclerView history_foodpage_cookingmethod;
    HistoryFoodPageAdapter historyFoodPageAdapter_cookingmethod;

    public HistoryFoodPageCookingMethod() {
        // Required empty public constructor
    }


    public static HistoryFoodPageCookingMethod newInstance() {
        HistoryFoodPageCookingMethod fragment = new HistoryFoodPageCookingMethod();
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
        View rootview = inflater.inflate(R.layout.fragment_history_food_page_cooking_method, container, false);
        initialize(rootview);
        return rootview;
    }

    private void initialize(View rootview){
        history_foodpage_cookingmethod = (RecyclerView)rootview.findViewById(R.id.history_cooking_recipe_method_list);

        historyFoodPageAdapter_cookingmethod = new HistoryFoodPageAdapter(history_foodpage_food.getCooking_method());
        history_foodpage_cookingmethod.setLayoutManager(new LinearLayoutManager(getContext()));
        history_foodpage_cookingmethod.setAdapter(historyFoodPageAdapter_cookingmethod);
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume(){
        super.onResume();
        ((HistoryFoodPageAdapter) historyFoodPageAdapter_cookingmethod).setOnItemClickListener(new HistoryFoodPageAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
            }
        });
    }
}
