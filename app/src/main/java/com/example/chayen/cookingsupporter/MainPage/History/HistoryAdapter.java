package com.example.chayen.cookingsupporter.MainPage.History;

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
 * Created by chayen on 25-Feb-17.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{

    private Context context;
    private MyClickListener mCallback;
    private ArrayList<FoodDatabaseClass> history_foodlist;

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_food, parent, false);
        HistoryViewHolder dataObjHolder = new HistoryViewHolder(view);
        context = parent.getContext();
        return dataObjHolder;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        holder.history_foodname.setText(history_foodlist.get(position).getFood_name());
        holder.history_foodtype.setText(history_foodlist.get(position).getFood_type());
        Picasso.with(context).load(history_foodlist.get(position).getFood_image()).into(holder.history_foodimage);
    }

    @Override
    public int getItemCount() {
        return history_foodlist.size();
    }

    public void setOnItemClickListener(MyClickListener mCallback){
        this.mCallback = mCallback;
    }

    public HistoryAdapter(ArrayList<FoodDatabaseClass> myDataset){
        history_foodlist = myDataset;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView history_foodname, history_foodtype;
        ImageView history_foodimage;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            history_foodname = (TextView)itemView.findViewById(R.id.food_list_name);
            history_foodtype = (TextView)itemView.findViewById(R.id.food_list_type);
            history_foodimage = (ImageView)itemView.findViewById(R.id.food_list_image);
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
