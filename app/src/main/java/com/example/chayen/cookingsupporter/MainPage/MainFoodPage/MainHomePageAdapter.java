package com.example.chayen.cookingsupporter.MainPage.MainFoodPage;

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
 * Created by Chayenjr on 10/3/2560.
 */

public class MainHomePageAdapter extends RecyclerView.Adapter<MainHomePageAdapter.MainHomePageViewHolder>{

    private ArrayList<FoodDatabaseClass> mDataset;
    private Context context;
    private MyClickListener mCallback;

    @Override
    public MainHomePageAdapter.MainHomePageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_food, parent, false);
        MainHomePageViewHolder dataObjHolder = new MainHomePageViewHolder(view);
        return dataObjHolder;
    }

    @Override
    public void onBindViewHolder(MainHomePageAdapter.MainHomePageViewHolder holder, int position) {
        holder.mainfood_foodname.setText(mDataset.get(position).getFood_name());
        holder.mainfood_foodtype.setText(mDataset.get(position).getFood_type());
        Picasso.with(context).load(mDataset.get(position).getFood_image()).into(holder.mainfood_foodimage);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnItemClickListener(MyClickListener mCallback){
        this.mCallback = mCallback;
    }

    public MainHomePageAdapter(ArrayList<FoodDatabaseClass> myDataset){
        mDataset = myDataset;
    }

    public class MainHomePageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mainfood_foodname, mainfood_foodtype;
        ImageView mainfood_foodimage;

        public MainHomePageViewHolder(View itemView) {
            super(itemView);
            mainfood_foodname = (TextView)itemView.findViewById(R.id.food_list_name);
            mainfood_foodtype = (TextView)itemView.findViewById(R.id.food_list_type);
            mainfood_foodimage = (ImageView)itemView.findViewById(R.id.food_list_image);
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
