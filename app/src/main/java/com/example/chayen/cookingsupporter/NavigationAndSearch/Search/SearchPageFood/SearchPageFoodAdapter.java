package com.example.chayen.cookingsupporter.NavigationAndSearch.Search.SearchPageFood;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.R;

import java.util.ArrayList;

/**
 * Created by chayen on 28-Mar-17.
 */

public class SearchPageFoodAdapter extends RecyclerView.Adapter<SearchPageFoodAdapter.SearchPageFoodViewHolder>{

    private ArrayList<String> searchpage_food_textlist;

    @Override
    public SearchPageFoodAdapter.SearchPageFoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cooking_recipe_adapter, parent, false);
        SearchPageFoodViewHolder searchPageFoodViewHolder = new SearchPageFoodViewHolder(view);
        return searchPageFoodViewHolder;
    }

    @Override
    public void onBindViewHolder(SearchPageFoodAdapter.SearchPageFoodViewHolder holder, int position) {
        holder.searchpage_text_recycler.setText(String.valueOf(position+1) + ". " + searchpage_food_textlist.get(position));
    }

    @Override
    public int getItemCount() {
        return searchpage_food_textlist.size();
    }

    SearchPageFoodAdapter(ArrayList<String> myDataset){
        searchpage_food_textlist = myDataset;
    }

    public class SearchPageFoodViewHolder extends RecyclerView.ViewHolder {

        private TextView searchpage_text_recycler;

        public SearchPageFoodViewHolder(View itemView) {
            super(itemView);
            searchpage_text_recycler = (TextView)itemView.findViewById(R.id.food_text_adapter);
        }
    }
}
