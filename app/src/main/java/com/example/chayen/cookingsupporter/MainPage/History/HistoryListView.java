package com.example.chayen.cookingsupporter.MainPage.History;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.R;
import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;
import com.squareup.picasso.Picasso;

/**
 * Created by chayen on 12-Mar-17.
 */

public class HistoryListView extends BaseCustomViewGroup{

    TextView historyfood_name, historyfood_type;
    ImageView historyfood_image, historyfood_rank;


    public HistoryListView(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_food, this);
    }

    private void initInstances() {
        // findViewById here
        historyfood_name = (TextView) findViewById(R.id.food_list_name);
        historyfood_type = (TextView) findViewById(R.id.food_list_type);
        historyfood_image = (ImageView) findViewById(R.id.food_list_image);
        historyfood_rank = (ImageView) findViewById(R.id.food_list_rank);
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

    public void setHistoryfood_name(String text){
        historyfood_name.setText(text);
    }

    public void setHistoryfood_type(String text){
        historyfood_type.setText(text);
    }

    public void setHistoryfood_image(String text){
        Picasso.with(getContext()).load(text).into(historyfood_image);
    }

    public void setHistoryfood_rank(int imageid){
        Picasso.with(getContext()).load(imageid).into(historyfood_rank);
    }
}
