package com.example.chayen.cookingsupporter.FoodListAdapter;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.R;
import com.squareup.picasso.Picasso;

import static com.example.chayen.cookingsupporter.MainPage.MainFoodPage.MainHomePageFragment.foodlist;

public class CookingRecipe extends AppCompatActivity {

    static int cooking_position;
    public static FoodDatabaseClass main_foodrecipe;
    TextView cooking_recipe_name, cooking_recipe_type;
    ImageView cooking_recipe_image;

    private ViewPager viewPager;
    private TabLayout tabLayout;

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
        viewPager = (ViewPager)findViewById(R.id.viewPager_foodpage);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout_foodpage);
    }

    private void renderRecipe(){
        Picasso.with(getApplicationContext()).load(foodlist.get(cooking_position).getFood_image()).into(cooking_recipe_image);
        cooking_recipe_name.setText(foodlist.get(cooking_position).getFood_name());
        cooking_recipe_type.setText(foodlist.get(cooking_position).getFood_type());
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
}
