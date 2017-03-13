package com.example.chayen.cookingsupporter.MainPage.History.HistoryListFoodPage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.R;

import java.util.ArrayList;

/**
 * Created by chayen on 13-Mar-17.
 */

public class HistoryFoodPageAdapter extends RecyclerView.Adapter<HistoryFoodPageAdapter.HistoryFoodPageViewHolder>{

    private ArrayList<String> history_foodpage_textlist;
    private Context context;
    private MyClickListener mCallback;

    @Override
    public HistoryFoodPageAdapter.HistoryFoodPageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cooking_recipe_adapter, parent, false);
        HistoryFoodPageViewHolder dataObjHolder = new HistoryFoodPageViewHolder(view);
        return dataObjHolder;
    }

    @Override
    public void onBindViewHolder(HistoryFoodPageAdapter.HistoryFoodPageViewHolder holder, int position) {
        holder.history_foodpage_recycler.setText(String.valueOf(position+1) + ". " + history_foodpage_textlist.get(position));
    }

    @Override
    public int getItemCount() {
        return history_foodpage_textlist.size();
    }

    public HistoryFoodPageAdapter(ArrayList<String> myDataset){
        history_foodpage_textlist = myDataset;
    }

    public void setOnItemClickListener(MyClickListener mCallback){
        this.mCallback = mCallback;
    }

    public class HistoryFoodPageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView history_foodpage_recycler;

        public HistoryFoodPageViewHolder(View itemView) {
            super(itemView);
            history_foodpage_recycler = (TextView)itemView.findViewById(R.id.food_text_adapter);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mCallback.onItemClick(getAdapterPosition(), view);
        }
    }

    public interface MyClickListener{
        public void onItemClick(int position, View v);
    }
}
