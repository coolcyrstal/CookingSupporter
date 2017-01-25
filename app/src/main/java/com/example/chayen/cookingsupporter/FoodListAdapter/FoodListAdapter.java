package com.example.chayen.cookingsupporter.FoodListAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by chayen on 23-Jan-17.
 */

public class FoodListAdapter extends BaseAdapter{

    String[] food_name, food_type, food_image;

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
}
