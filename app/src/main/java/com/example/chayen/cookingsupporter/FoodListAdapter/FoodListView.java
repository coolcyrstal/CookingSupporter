package com.example.chayen.cookingsupporter.FoodListAdapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.R;
import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;

/**
 * Created by chayen on 23-Jan-17.
 */

public class FoodListView extends BaseCustomViewGroup {

    TextView food_name, star_value;

    public FoodListView(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_food, this);
    }

    private void initInstances() {
        // findViewById here
        food_name = (TextView) findViewById(R.id.food_list_name);
        star_value = (TextView) findViewById(R.id.food_list_star);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

    public void setFood_name(String text){
        food_name.setText(text);
    }

    public void setStar_value(String text){
        star_value.setText(text);
    }
}
