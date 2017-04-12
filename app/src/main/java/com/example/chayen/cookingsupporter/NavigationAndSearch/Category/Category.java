package com.example.chayen.cookingsupporter.NavigationAndSearch.Category;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.example.chayen.cookingsupporter.R;
import com.squareup.picasso.Picasso;

public class Category extends AppCompatActivity {

    private ImageView Main_dish, Seafood, Steaming, Grilling, Boiling,
            Frying_food, Deep_Frying_food, Other;
    private CardView main_dish_card, seafood_card, steaming_card, grilling_card,
            boiling_card, frying_card, deep_frying_card, other_card;

    int[] category_image_array = {
            R.drawable.rsz_main_dish,
            R.drawable.rsz_seafood,
            R.drawable.rsz_steaming_category,
            R.drawable.rsz_grilling_category,
            R.drawable.boiling_category,
            R.drawable.frying_category,
            R.drawable.rsz_deep_frying_category,
            R.drawable.rsz_other_category
    };

    ImageView[] category_imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initialize();
    }

    private void initialize(){
        Main_dish = (ImageView)findViewById(R.id.main_dish_image);
        Seafood = (ImageView)findViewById(R.id.seafood_image);
        Steaming = (ImageView)findViewById(R.id.steaming_image);
        Grilling = (ImageView)findViewById(R.id.grilling_image);
        Boiling = (ImageView)findViewById(R.id.boiling_image);
        Frying_food = (ImageView)findViewById(R.id.frying_image);
        Deep_Frying_food = (ImageView)findViewById(R.id.deep_frying_image);
        Other = (ImageView)findViewById(R.id.other_image);

        category_imageview = new ImageView[8];
        setCategory_imageview();

        main_dish_card = (CardView)findViewById(R.id.main_dish_card);
        seafood_card = (CardView)findViewById(R.id.seafood_card);
        steaming_card = (CardView)findViewById(R.id.steaming_card);
        grilling_card = (CardView)findViewById(R.id.grilling_card);
        boiling_card = (CardView)findViewById(R.id.boiling_card);
        frying_card = (CardView)findViewById(R.id.frying_card);
        deep_frying_card = (CardView)findViewById(R.id.deep_frying_card);
        other_card = (CardView)findViewById(R.id.other_card);

        loadResourceImage();
        categoryCardOnClick();
    }

    private void setCategory_imageview(){
        category_imageview[0] = Main_dish;
        category_imageview[1] = Seafood;
        category_imageview[2] = Steaming;
        category_imageview[3] = Grilling;
        category_imageview[4] = Boiling;
        category_imageview[5] = Frying_food;
        category_imageview[6] = Deep_Frying_food;
        category_imageview[7] = Other;
    }

    private void loadResourceImage(){
        for(int i = 0; i < category_imageview.length; i++){
            Picasso.with(getApplicationContext()).load(category_image_array[i]).into(category_imageview[i]);
        }
    }

    private void categoryCardOnClick(){
        main_dish_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToEachCategoryPage("Main Dish");
            }
        });

        seafood_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToEachCategoryPage("Seafood");
            }
        });

        steaming_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToEachCategoryPage("Steaming");
            }
        });

        grilling_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToEachCategoryPage("Grilling");
            }
        });

        boiling_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToEachCategoryPage("Boiling");
            }
        });

        frying_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToEachCategoryPage("Frying");
            }
        });

        deep_frying_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToEachCategoryPage("Deep Frying");
            }
        });

        other_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToEachCategoryPage("Other");
            }
        });
    }

    private void intentToEachCategoryPage(String category){
        Intent intent = new Intent(Category.this, CategoryPage.class);
        startActivity(intent);
        CategoryPage.category = category;
    }
}
