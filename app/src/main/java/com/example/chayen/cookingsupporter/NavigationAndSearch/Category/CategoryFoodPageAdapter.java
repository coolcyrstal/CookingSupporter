package com.example.chayen.cookingsupporter.NavigationAndSearch.Category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.R;

import java.util.ArrayList;

/**
 * Created by chayen on 22-Feb-17.
 */

public class CategoryFoodPageAdapter extends RecyclerView.Adapter<CategoryFoodPageAdapter.CategoryViewHolder>{

    private ArrayList<String> category_foodpage_textlist;
    private Context context;
    private MyClickListener mCallback;

    @Override
    public CategoryFoodPageAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cooking_recipe_adapter, parent, false);
        CategoryViewHolder dataObjHolder = new CategoryViewHolder(view);
        context = parent.getContext();
//        Log.d("inheritfood testadapter", ""+ category_foodpage_textlist.size());
        return dataObjHolder;
    }

    @Override
    public void onBindViewHolder(CategoryFoodPageAdapter.CategoryViewHolder holder, int position) {
        holder.category_foodpage_recycler.setText(String.valueOf(position+1) + ". " + category_foodpage_textlist.get(position));
//        Log.d("inheritfood testadapter", ""+ category_foodpage_textlist.get(position));
    }

    @Override
    public int getItemCount(){
//        Log.d("inheritfood testadapter", ""+ category_foodpage_textlist.size());
        return category_foodpage_textlist.size();
    }

    public void setOnItemClickListener(MyClickListener mCallback){
        this.mCallback = mCallback;
    }

    public CategoryFoodPageAdapter(ArrayList<String> myDataset){
        category_foodpage_textlist = myDataset;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView category_foodpage_recycler;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            category_foodpage_recycler = (TextView)itemView.findViewById(R.id.food_text_adapter);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mCallback.onItemClick(getAdapterPosition(), v);
        }
    }

    public interface MyClickListener{
        public void onItemClick(int position, View v);
    }
}
