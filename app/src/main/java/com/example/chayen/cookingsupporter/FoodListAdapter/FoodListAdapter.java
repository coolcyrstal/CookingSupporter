package com.example.chayen.cookingsupporter.FoodListAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by chayen on 23-Jan-17.
 */

public class FoodListAdapter extends BaseAdapter{

    String[] food_name, star_value;

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
        foodListView.setStar_value(star_value[position]);
        return foodListView;
    }

    public void setFood_name(String[] food_name){
        this.food_name = food_name;
    }

    public void setStar_value(String[] star_value){
        this.star_value = star_value;
    }
}
