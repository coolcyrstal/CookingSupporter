package com.example.chayen.cookingsupporter.FoodListAdapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.R;
import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by chayen on 23-Jan-17.
 */

public class FoodListView extends BaseCustomViewGroup {

    private TextView food_name, food_type;
    private ImageView food_image, food_rank;

    public FoodListView(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

//    public FoodListView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        initInflate();
//        initInstances();
//    }

//    public FoodListView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        initInflate();
//        initInstances();
//    }

//    @TargetApi(21)
//    public FoodListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        initInflate();
//        initInstances();
//    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_food, this);
    }

    private void initInstances() {
        // findViewById here
        food_name = (TextView) findViewById(R.id.food_list_name);
        food_type = (TextView) findViewById(R.id.food_list_type);
        food_image = (ImageView) findViewById(R.id.food_list_image);
        food_rank = (ImageView) findViewById(R.id.food_list_rank);
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

    public void setFood_type(String text){
        food_type.setText(text);
    }

    public void setFood_image(String text){
        Picasso.with(getContext()).load(text).into(food_image);
//        Log.d("testfood_image", text);
//        try {
//            URL url = new URL(text);
//            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            food_image.setImageBitmap(bmp);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        food_image.setImageURI(Uri.parse(text));
    }

    public void setFood_rank(int imageid) {
        Picasso.with(getContext()).load(imageid).into(food_rank);
    }
}
