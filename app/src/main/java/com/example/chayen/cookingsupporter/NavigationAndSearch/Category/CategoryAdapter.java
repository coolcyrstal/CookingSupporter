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
    private int[] image_star_rank = {
            R.drawable.rsz_star_0,
            R.drawable.rsz_star_0_5,
            R.drawable.rsz_star_1,
            R.drawable.rsz_star_1_5,
            R.drawable.rsz_star_2,
            R.drawable.rsz_star_2_5,
            R.drawable.rsz_star_3,
            R.drawable.rsz_star_3_5,
            R.drawable.rsz_star_4,
            R.drawable.rsz_star_4_5,
            R.drawable.rsz_star_5
    };

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
        Picasso.with(context).load(foodrank(position)).into(holder.category_foodrank);
    }

    private int foodrank(int position){
        Double user_count, star_count, star_value;
        user_count = mDataset.get(position).getUser_count().doubleValue();
        star_count = mDataset.get(position).getStar_count().doubleValue();
        if(user_count == 0 || star_count == 0){
            return image_star_rank[0];
        }
        else{
            star_value = star_count/user_count;
            if(star_value <= 0.5){
                return image_star_rank[1];
            } else if(star_value > 0.5 && star_value <= 1){
                return image_star_rank[2];
            } else if(star_value > 1 && star_value <= 1.5){
                return image_star_rank[3];
            } else if(star_value > 1.5 && star_value <= 2){
                return image_star_rank[4];
            } else if(star_value > 2 && star_value <= 2.5){
                return image_star_rank[5];
            } else if(star_value > 2.5 && star_value <= 3){
                return image_star_rank[6];
            } else if(star_value > 3 && star_value <= 3.5){
                return image_star_rank[7];
            } else if(star_value > 3.5 && star_value <= 4){
                return image_star_rank[8];
            } else if(star_value > 4 && star_value <= 4.5){
                return image_star_rank[9];
            } else {
                return image_star_rank[10];
            }
        }
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
        private ImageView category_foodrank;

        public ViewHolder(View itemView){
            super(itemView);
            category_foodname = (TextView)itemView.findViewById(R.id.category_food_name);
            category_foodimage = (ImageView)itemView.findViewById(R.id.category_food_image);
            category_foodrank = (ImageView)itemView.findViewById(R.id.category_food_list_rank);
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
