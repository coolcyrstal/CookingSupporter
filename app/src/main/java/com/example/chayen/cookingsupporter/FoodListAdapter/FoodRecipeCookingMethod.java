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


public class FoodRecipeCookingMethod extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView foodpage_cookingmethod;
    private FoodRecipeAdapter foodRecipeAdapter_cookingmethod;

    public FoodRecipeCookingMethod() {
        // Required empty public constructor
    }

    public static FoodRecipeCookingMethod newInstance() {
        FoodRecipeCookingMethod fragment = new FoodRecipeCookingMethod();
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
        View rootview = inflater.inflate(R.layout.fragment_food_recipe_cooking_method, container, false);
        initialize(rootview);
        return rootview;
    }

    private void initialize(View rootview){
        foodpage_cookingmethod = (RecyclerView)rootview.findViewById(R.id.cooking_recipe_method_list);

        foodRecipeAdapter_cookingmethod = new FoodRecipeAdapter(main_foodrecipe.getIngredient());
        foodpage_cookingmethod.setLayoutManager(new LinearLayoutManager(getContext()));
        foodpage_cookingmethod.setAdapter(foodRecipeAdapter_cookingmethod);
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
