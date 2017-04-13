package com.example.chayen.cookingsupporter.FoodListAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.chayen.cookingsupporter.R;

import java.util.ArrayList;

/**
 * Created by chayen on 23-Jan-17.
 */

public class FoodListAdapter extends BaseAdapter{

    private String[] food_name, food_type, food_image;
    private ArrayList<Long> food_usercount, food_star_count;
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

    public int getCount() {
        if (food_name == null)
            return 0;
        else
            return food_name.length;
    }

    public long getItemId(int position) {
        return 0;
    }

    public Object getItem(int position) {
        return null;
    }

    public View getView(int position, View view, ViewGroup parent) {
        FoodListView foodListView = new FoodListView(parent.getContext());
        foodListView.setFood_name(food_name[position]);
        foodListView.setFood_type(food_type[position]);
        foodListView.setFood_image(food_image[position]);
        foodListView.setFood_rank(foodrank(position));
        return foodListView;
    }

    public void setFood_name(String[] food_name){
        this.food_name = food_name;
    }

    public void setFood_type(String[] food_type){
        this.food_type = food_type;
    }

    public void setFood_image(String[] food_image){
        this.food_image = food_image;
    }

    public void setFood_usercount(ArrayList<Long> food_usercount) {
        this.food_usercount = food_usercount;
    }

    public void setFood_star_count(ArrayList<Long> food_star_count) {
        this.food_star_count = food_star_count;
    }

    private int foodrank(int position){
        Double user_count, star_count, star_value;
        user_count = food_usercount.get(position).doubleValue() + position;
        star_count = food_star_count.get(position).doubleValue() + 2;
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
}
