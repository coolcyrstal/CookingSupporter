package com.example.chayen.cookingsupporter.NavigationAndSearch.Category.CategoryFoodPage;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.R;
import com.squareup.picasso.Picasso;

public class CategoryFoodPage extends AppCompatActivity {

    ImageView category_foodpage_image;
    TextView category_foodpage_name, category_foodpage_type;

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
        viewPager = (ViewPager) findViewById(R.id.viewPager_category_foodpage);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout_category_foodpage);

        Picasso.with(getApplicationContext()).load(category_food.getFood_image()).into(category_foodpage_image);
        category_foodpage_name.setText(category_food.getFood_name());
        category_foodpage_type.setText(category_food.getFood_type());
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
