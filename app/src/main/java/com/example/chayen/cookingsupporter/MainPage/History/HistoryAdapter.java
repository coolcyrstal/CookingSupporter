package com.example.chayen.cookingsupporter.MainPage.History;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private Context context, history_context;
    private MyClickListener mCallback;
    private ArrayList<FoodDatabaseClass> history_foodlist;
    private ArrayList<String> history_foodlistname, history_foodlisttype, history_foodlistimage;

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(history_context).inflate(R.layout.list_food, parent, false);
        HistoryViewHolder dataObjHolder = new HistoryViewHolder(view);
        context = parent.getContext();
        return dataObjHolder;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        holder.history_foodname.setText(history_foodlist.get(position).getFood_name());
        Log.d("testhistorybind", history_foodlist.get(position).getFood_name() + history_foodlist.size());
        holder.history_foodtype.setText(history_foodlist.get(position).getFood_type());
        Picasso.with(context).load(history_foodlist.get(position).getFood_image()).into(holder.history_foodimage);
//        holder.history_foodname.setText(history_foodlistname.get(position));
//        holder.history_foodtype.setText(history_foodlisttype.get(position));
//        Picasso.with(context).load(history_foodlistimage.get(position)).into(holder.history_foodimage);
//        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        if(history_foodlist == null)
            return 0;
        else
            return history_foodlist.size();
//        return history_foodlistname.size();
//        Log.d("testhistory", "" + history_foodlist.size());
    }

//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//    }

    public void setOnItemClickListener(MyClickListener mCallback){
        this.mCallback = mCallback;
    }

    public HistoryAdapter(Context history_context, ArrayList<FoodDatabaseClass> myDataset){
        this.history_context = history_context;
        history_foodlist = myDataset;
    }

//    public HistoryAdapter(ArrayList<String> history_foodlistname, ArrayList<String> history_foodlisttype,
//                          ArrayList<String> history_foodlistimage){
//        this.history_foodlistname = history_foodlistname;
//        this.history_foodlisttype = history_foodlisttype;
//        this.history_foodlistimage = history_foodlistimage;
//    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView history_foodname, history_foodtype;
        private ImageView history_foodimage;

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
