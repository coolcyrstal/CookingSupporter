package com.example.chayen.cookingsupporter.NavigationAndSearch.Category.CategoryFoodPage;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.R;
import com.squareup.picasso.Picasso;

public class CategoryFoodPage extends AppCompatActivity {

    private ImageView category_foodpage_image;
    private TextView category_foodpage_name, category_foodpage_type;
    private Button category_foodpage_starbutton_1, category_foodpage_starbutton_2,
            category_foodpage_starbutton_3, category_foodpage_starbutton_4, category_foodpage_starbutton_5;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    public static FoodDatabaseClass category_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_food_page);

        initialize();
        setViewPager();
    }

    private void initialize(){
        category_foodpage_image = (ImageView)findViewById(R.id.category_cooking_recipe_foodimage);
        category_foodpage_name = (TextView)findViewById(R.id.category_cooking_recipe_foodname);
        category_foodpage_type = (TextView)findViewById(R.id.category_cooking_recipe_foodtype);
        category_foodpage_starbutton_1 = (Button)findViewById(R.id.category_star_button_1);
        category_foodpage_starbutton_2 = (Button)findViewById(R.id.category_star_button_2);
        category_foodpage_starbutton_3 = (Button)findViewById(R.id.category_star_button_3);
        category_foodpage_starbutton_4 = (Button)findViewById(R.id.category_star_button_4);
        category_foodpage_starbutton_5 = (Button)findViewById(R.id.category_star_button_5);
        viewPager = (ViewPager) findViewById(R.id.viewPager_category_foodpage);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout_category_foodpage);

        Picasso.with(getApplicationContext()).load(category_food.getFood_image()).into(category_foodpage_image);
        category_foodpage_name.setText(category_food.getFood_name());
        category_foodpage_type.setText(category_food.getFood_type());

        category_starbuttonOnclick();
    }

    private void category_starbuttonOnclick(){
        category_foodpage_starbutton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                category_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button);
                category_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button);
                category_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button);
                category_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button);
            }
        });

        category_foodpage_starbutton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                category_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button_fill);
                category_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button);
                category_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button);
                category_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button);
            }
        });

        category_foodpage_starbutton_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                category_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button_fill);
                category_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button_fill);
                category_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button);
                category_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button);
            }
        });

        category_foodpage_starbutton_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                category_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button_fill);
                category_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button_fill);
                category_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button_fill);
                category_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button);
            }
        });

        category_foodpage_starbutton_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                category_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button_fill);
                category_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button_fill);
                category_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button_fill);
                category_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button_fill);
            }
        });
    }

    private void setViewPager() {
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return CategoryFoodPageIngredient.newInstance();
                    case 1:
                        return CategoryFoodPageCookingMethod.newInstance();
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
}
