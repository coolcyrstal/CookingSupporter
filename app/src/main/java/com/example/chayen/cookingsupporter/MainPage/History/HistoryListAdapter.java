package com.example.chayen.cookingsupporter.MainPage.History;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.chayen.cookingsupporter.R;

import java.util.ArrayList;

/**
 * Created by chayen on 12-Mar-17.
 */

public class HistoryListAdapter extends BaseAdapter{

    private ArrayList<String> historyfood_name, historyfood_type, historyfood_image;
    private ArrayList<Long> historyfood_usercount, historyfood_star_count;
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
    public int getCount() {
        if (historyfood_name == null)
            return 0;
        else
            return historyfood_name.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        HistoryListView historyListView = new HistoryListView(viewGroup.getContext());
        historyListView.setHistoryfood_name(historyfood_name.get(position));
        historyListView.setHistoryfood_type(historyfood_type.get(position));
        historyListView.setHistoryfood_image(historyfood_image.get(position));
        historyListView.setHistoryfood_rank(foodrank(position));
        return historyListView;
    }

    public void setHistoryfood_name(ArrayList<String> historyfood_name) {
        this.historyfood_name = historyfood_name;
    }

    public void setHistoryfood_type(ArrayList<String> historyfood_type) {
        this.historyfood_type = historyfood_type;
    }

    public void setHistoryfood_image(ArrayList<String> historyfood_image) {
        this.historyfood_image = historyfood_image;
    }

    private int foodrank(int position){
        Double user_count, star_count, star_value;
        user_count = historyfood_usercount.get(position).doubleValue() + position;
        star_count = historyfood_star_count.get(position).doubleValue() + 2;
        if(user_count == 0 || star_count == 0){
            return image_star_rank[0];
        }
        else{
            star_value = user_count/star_count;
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

    public void setHistoryfood_usercount(ArrayList<Long> historyfood_usercount) {
        this.historyfood_usercount = historyfood_usercount;
    }

    public void setHistoryfood_star_count(ArrayList<Long> historyfood_star_count) {
        this.historyfood_star_count = historyfood_star_count;
    }
}
