package com.example.chayen.cookingsupporter.FoodListAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.R;

import java.util.ArrayList;

/**
 * Created by Chayenjr on 3/3/2560.
 */

public class FoodRecipeAdapter extends RecyclerView.Adapter<FoodRecipeAdapter.FoodRecipeViewHolder>{

    private ArrayList<String> foodpage_textlist;

    @Override
    public FoodRecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cooking_recipe_adapter, parent, false);
        FoodRecipeViewHolder dataObjHolder = new FoodRecipeViewHolder(view);

        return dataObjHolder;
    }

    @Override
    public void onBindViewHolder(FoodRecipeViewHolder holder, int position) {
        holder.food_recipe_textview.setText(String.valueOf(position+1) + ". " + foodpage_textlist.get(position));
    }

    @Override
    public int getItemCount() {
        return foodpage_textlist.size();
    }

    public FoodRecipeAdapter(ArrayList<String> myDataset){
        foodpage_textlist = myDataset;
    }

    public class FoodRecipeViewHolder extends RecyclerView.ViewHolder {
        TextView food_recipe_textview;
        public FoodRecipeViewHolder(View itemView) {
            super(itemView);
            food_recipe_textview = (TextView)itemView.findViewById(R.id.food_text_adapter);
        }
    }
}
