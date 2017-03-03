package com.example.chayen.cookingsupporter.FoodListAdapter;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chayen.cookingsupporter.R;

import static com.example.chayen.cookingsupporter.FoodListAdapter.CookingRecipe.main_foodrecipe;

public class FoodRecipeIngredient extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView foodpage_ingredient;
    private FoodRecipeAdapter foodRecipeAdapter_ingredient;

    public FoodRecipeIngredient() {
        // Required empty public constructor
    }

    public static FoodRecipeIngredient newInstance() {
        FoodRecipeIngredient fragment = new FoodRecipeIngredient();
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
        View rootview = inflater.inflate(R.layout.fragment_food_recipe_ingredient, container, false);
        initialize(rootview);
        return rootview;
    }

    private void initialize(View rootview){
        foodpage_ingredient = (RecyclerView)rootview.findViewById(R.id.cooking_recipe_ingredient_list);

        foodRecipeAdapter_ingredient = new FoodRecipeAdapter(main_foodrecipe.getIngredient());
        foodpage_ingredient.setLayoutManager(new LinearLayoutManager(getContext()));
        foodpage_ingredient.setAdapter(foodRecipeAdapter_ingredient);
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
