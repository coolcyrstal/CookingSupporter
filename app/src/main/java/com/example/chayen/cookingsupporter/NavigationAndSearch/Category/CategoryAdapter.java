package com.example.chayen.cookingsupporter.NavigationAndSearch.Category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by chayen on 15-Feb-17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    private MyClickListener mCallback;
    private ArrayList<FoodDatabaseClass> mDataset;
    private Context context;

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_category_food, parent, false);
        ViewHolder dataObjHolder = new ViewHolder(view);
        context = parent.getContext();
//        Log.d("inheritfood testadapter", ""+ mDataset.get(0).getFood_name());
        return dataObjHolder;
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        holder.category_foodname.setText(mDataset.get(position).getFood_name());
//        Log.d("inheritfood testadapter", ""+ mDataset.get(0).getFood_name());
        Picasso.with(context).load(mDataset.get(position).getFood_image()).into(holder.category_foodimage);
    }

    @Override
    public int getItemCount() {
//        Log.d("inheritfood test", ""+ mDataset.size());
        return mDataset.size();
    }

    public void setOnItemClickListener(MyClickListener mCallback){
        this.mCallback = mCallback;
    }

    public CategoryAdapter(ArrayList<FoodDatabaseClass> myDataset){
        mDataset = myDataset;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView category_foodname;
        private ImageView category_foodimage;

        public ViewHolder(View itemView){
            super(itemView);
            category_foodname = (TextView)itemView.findViewById(R.id.category_food_name);
            category_foodimage = (ImageView)itemView.findViewById(R.id.category_food_image);
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
