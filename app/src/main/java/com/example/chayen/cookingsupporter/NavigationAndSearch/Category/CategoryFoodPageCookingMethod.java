package com.example.chayen.cookingsupporter.NavigationAndSearch.Category;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chayen.cookingsupporter.R;

import static com.example.chayen.cookingsupporter.NavigationAndSearch.Category.CategoryFoodPage.category_food;

public class CategoryFoodPageCookingMethod extends Fragment {

    private OnFragmentInteractionListener mListener;
    RecyclerView category_foodpage_cookingmethod;
    CategoryFoodPageAdapter categoryFoodPageAdapter_cookingmethod;

    public CategoryFoodPageCookingMethod() {
        // Required empty public constructor
    }

    public static CategoryFoodPageCookingMethod newInstance() {
        CategoryFoodPageCookingMethod fragment = new CategoryFoodPageCookingMethod();
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
        View rootview = inflater.inflate(R.layout.fragment_category_food_page_cooking_method, container, false);
        return rootview;
    }

    private void initialize(View rootview){
        category_foodpage_cookingmethod = (RecyclerView)rootview.findViewById(R.id.category_cooking_recipe_method_list);

        categoryFoodPageAdapter_cookingmethod = new CategoryFoodPageAdapter(category_food.getCooking_method());
        category_foodpage_cookingmethod.setLayoutManager(new LinearLayoutManager(getContext()));
        category_foodpage_cookingmethod.setAdapter(categoryFoodPageAdapter_cookingmethod);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

//    @Override
//    public void onResume(){
//        super.onResume();
//        ((CategoryFoodPageAdapter) categoryFoodPageAdapter_cookingmethod).setOnItemClickListener(new CategoryFoodPageAdapter.MyClickListener() {
//            @Override
//            public void onItemClick(int position, View v) {
//            }
//        });
//    }
}
