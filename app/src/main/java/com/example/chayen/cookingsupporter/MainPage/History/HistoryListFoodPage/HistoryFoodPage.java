package com.example.chayen.cookingsupporter.MainPage.History.HistoryListFoodPage;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.chayen.cookingsupporter.R;
import com.squareup.picasso.Picasso;

import static com.example.chayen.cookingsupporter.MainPage.History.HistoryListFragment.history_foodpage_food;

public class HistoryFoodPage extends AppCompatActivity {

    private ImageView history_foodpage_image;
    private TextView history_foodpage_name, history_foodpage_type;



    private ViewPager historyfoodpage_viewPager;
    private TabLayout historyfoodpage_tabLayout;

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
        historyfoodpage_viewPager = (ViewPager) findViewById(R.id.viewPager_history_foodpage);
        historyfoodpage_tabLayout = (TabLayout) findViewById(R.id.tabLayout_history_foodpage);

        Picasso.with(getApplicationContext()).load(history_foodpage_food.getFood_image()).into(history_foodpage_image);
        history_foodpage_name.setText(history_foodpage_food.getFood_name());
        history_foodpage_type.setText(history_foodpage_food.getFood_type());
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
}
