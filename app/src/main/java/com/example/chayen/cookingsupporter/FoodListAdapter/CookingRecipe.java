package com.example.chayen.cookingsupporter.FoodListAdapter;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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

import static com.example.chayen.cookingsupporter.MainPage.MainFoodPage.MainHomePageFragment.foodlist;

public class CookingRecipe extends AppCompatActivity {

    static int cooking_position;
    public static FoodDatabaseClass main_foodrecipe;
    private TextView cooking_recipe_name, cooking_recipe_type;
    private ImageView cooking_recipe_image;
    private Button mainpage_foodpage_starbutton_1, mainpage_foodpage_starbutton_2,
            mainpage_foodpage_starbutton_3, mainpage_foodpage_starbutton_4, mainpage_foodpage_starbutton_5;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private int user_count, star_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_recipe);
        initInstance();
        renderRecipe();
        setViewPager();
    }

    public static void getFoodRecipePosition(int position){
        cooking_position = position;
    }

    private void initInstance(){
        main_foodrecipe = foodlist.get(cooking_position);

        cooking_recipe_image = (ImageView) findViewById(R.id.cooking_recipe_foodimage);
        cooking_recipe_name = (TextView)findViewById(R.id.cooking_recipe_foodname);
        cooking_recipe_type = (TextView)findViewById(R.id.cooking_recipe_foodtype);
        mainpage_foodpage_starbutton_1 = (Button)findViewById(R.id.mainpage_star_button_1);
        mainpage_foodpage_starbutton_2 = (Button)findViewById(R.id.mainpage_star_button_2);
        mainpage_foodpage_starbutton_3 = (Button)findViewById(R.id.mainpage_star_button_3);
        mainpage_foodpage_starbutton_4 = (Button)findViewById(R.id.mainpage_star_button_4);
        mainpage_foodpage_starbutton_5 = (Button)findViewById(R.id.mainpage_star_button_5);
        viewPager = (ViewPager)findViewById(R.id.viewPager_foodpage);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout_foodpage);

        mainpage_starbuttonOnclick();
    }

    private void renderRecipe(){
        Picasso.with(getApplicationContext()).load(foodlist.get(cooking_position).getFood_image()).into(cooking_recipe_image);
        cooking_recipe_name.setText(foodlist.get(cooking_position).getFood_name());
        cooking_recipe_type.setText(foodlist.get(cooking_position).getFood_type());
        user_count = foodlist.get(cooking_position).getUser_count().intValue();
    }

    private void mainpage_starbuttonOnclick(){
        mainpage_foodpage_starbutton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainpage_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                mainpage_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button);
                mainpage_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button);
                mainpage_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button);
                mainpage_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button);
                star_count = 1;
            }
        });

        mainpage_foodpage_starbutton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainpage_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                mainpage_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button_fill);
                mainpage_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button);
                mainpage_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button);
                mainpage_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button);
                star_count = 2;
            }
        });

        mainpage_foodpage_starbutton_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainpage_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                mainpage_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button_fill);
                mainpage_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button_fill);
                mainpage_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button);
                mainpage_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button);
                star_count = 3;
            }
        });

        mainpage_foodpage_starbutton_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainpage_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                mainpage_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button_fill);
                mainpage_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button_fill);
                mainpage_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button_fill);
                mainpage_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button);
                star_count = 4;
            }
        });

        mainpage_foodpage_starbutton_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainpage_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                mainpage_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button_fill);
                mainpage_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button_fill);
                mainpage_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button_fill);
                mainpage_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button_fill);
                star_count = 5;
            }
        });
    }

    private void setViewPager() {
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return FoodRecipeIngredient.newInstance();
                    case 1:
                        return FoodRecipeCookingMethod.newInstance();
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
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed(){
        user_count++;
        star_count += foodlist.get(cooking_position).getStar_count();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("food");
        Query query1 = myRef.orderByChild("food_name").equalTo(foodlist.get(cooking_position).getFood_name());
//        Query query2 = query1.orderByChild("food_image").equalTo(foodlist.get(cooking_position).getFood_image());
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    if(snapshot.child("food_image").getValue().toString().equals(foodlist.get(cooking_position).getFood_image())){
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
        CookingRecipe.super.onBackPressed();
    }
}
