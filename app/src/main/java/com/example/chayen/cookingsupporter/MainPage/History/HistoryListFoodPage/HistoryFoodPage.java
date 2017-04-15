package com.example.chayen.cookingsupporter.MainPage.History.HistoryListFoodPage;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.chayen.cookingsupporter.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static com.example.chayen.cookingsupporter.MainPage.History.HistoryListFragment.history_foodpage_food;

public class HistoryFoodPage extends AppCompatActivity {

    private ImageView history_foodpage_image;
    private TextView history_foodpage_name, history_foodpage_type;
    private Button history_foodpage_starbutton_1, history_foodpage_starbutton_2,
            history_foodpage_starbutton_3, history_foodpage_starbutton_4, history_foodpage_starbutton_5;


    private ViewPager historyfoodpage_viewPager;
    private TabLayout historyfoodpage_tabLayout;

    private int user_count, star_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_food_page);
        initialize();
        setViewPager();
    }

    private void initialize(){
        history_foodpage_image = (ImageView)findViewById(R.id.history_cooking_recipe_foodimage);
        history_foodpage_name = (TextView)findViewById(R.id.history_cooking_recipe_foodname);
        history_foodpage_type = (TextView)findViewById(R.id.history_cooking_recipe_foodtype);
        history_foodpage_starbutton_1 = (Button)findViewById(R.id.history_star_button_1);
        history_foodpage_starbutton_2 = (Button)findViewById(R.id.history_star_button_2);
        history_foodpage_starbutton_3 = (Button)findViewById(R.id.history_star_button_3);
        history_foodpage_starbutton_4 = (Button)findViewById(R.id.history_star_button_4);
        history_foodpage_starbutton_5 = (Button)findViewById(R.id.history_star_button_5);
        historyfoodpage_viewPager = (ViewPager) findViewById(R.id.viewPager_history_foodpage);
        historyfoodpage_tabLayout = (TabLayout) findViewById(R.id.tabLayout_history_foodpage);

        Picasso.with(getApplicationContext()).load(history_foodpage_food.getFood_image()).into(history_foodpage_image);
        history_foodpage_name.setText(history_foodpage_food.getFood_name());
        history_foodpage_type.setText(history_foodpage_food.getFood_type());

        user_count = history_foodpage_food.getUser_count().intValue();
        history_starbuttonOnclick();
    }

    private void history_starbuttonOnclick(){
        history_foodpage_starbutton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                history_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button);
                history_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button);
                history_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button);
                history_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button);
                star_count = 1;
            }
        });

        history_foodpage_starbutton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                history_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button_fill);
                history_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button);
                history_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button);
                history_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button);
                star_count = 2;
            }
        });

        history_foodpage_starbutton_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                history_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button_fill);
                history_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button_fill);
                history_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button);
                history_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button);
                star_count = 3;
            }
        });

        history_foodpage_starbutton_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                history_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button_fill);
                history_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button_fill);
                history_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button_fill);
                history_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button);
                star_count = 4;
            }
        });

        history_foodpage_starbutton_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                history_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button_fill);
                history_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button_fill);
                history_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button_fill);
                history_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button_fill);
                star_count = 5;
            }
        });
    }

    private void setViewPager() {
        historyfoodpage_viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return HistoryFoodPageIngredient.newInstance();
                    case 1:
                        return HistoryFoodPageCookingMethod.newInstance();
                    default:
                        return null;
                }

            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Ingredient";
                    case 1:
                        return "Cooking Method";
                    default:
                        return "";
                }
            }
        });
        historyfoodpage_tabLayout.setupWithViewPager(historyfoodpage_viewPager);
    }

    @Override
    public void onBackPressed(){
        if(star_count != 0){
            user_count++;
            star_count += history_foodpage_food.getStar_count();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference().child("food");
            Query query1 = myRef.orderByChild("food_name").equalTo(history_foodpage_food.getFood_name());
            query1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        if(snapshot.child("food_image").getValue().toString().equals(history_foodpage_food.getFood_image())){
                            snapshot.getRef().child("star_count").setValue(star_count);
                            snapshot.getRef().child("user_count").setValue(user_count);
//                        Log.d("test send starvalue", "" + star_count + ":" + user_count);
                        }
//                    Log.d("test send starvalue", "" + snapshot.child("food_image").getValue().equals(foodlist.get(cooking_position).getFood_image()) + ":" + snapshot.child("food_name").getValue());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("test send starvalue", "" + databaseError.getMessage());
                }
            });
        }
        HistoryFoodPage.super.onBackPressed();
    }
}
