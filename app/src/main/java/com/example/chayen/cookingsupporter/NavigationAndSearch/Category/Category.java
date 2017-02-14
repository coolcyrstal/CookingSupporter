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

    ImageView Steaming, Grilling, Roasting, Boiling,
            Stewing, Frying_food, Deep_Frying_food, Other;
    CardView steaming_card, grilling_card, roasting_card, boiling_card,
            stewing_card, frying_card, deep_frying_card, other_card;

    int[] category_image_array = {
            R.drawable.rsz_steaming_category,
            R.drawable.grilling_category,
            R.drawable.rsz_roasting_category,
            R.drawable.boiling_category,
            R.drawable.stewing_category,
            R.drawable.frying_category,
            R.drawable.deep_frying_category,
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
        Steaming = (ImageView)findViewById(R.id.steaming_image);
        Grilling = (ImageView)findViewById(R.id.grilling_image);
        Roasting = (ImageView)findViewById(R.id.roasting_image);
        Boiling = (ImageView)findViewById(R.id.boiling_image);
        Stewing = (ImageView)findViewById(R.id.stewing_image);
        Frying_food = (ImageView)findViewById(R.id.frying_image);
        Deep_Frying_food = (ImageView)findViewById(R.id.deep_frying_image);
        Other = (ImageView)findViewById(R.id.other_image);

        category_imageview = new ImageView[8];
        setCategory_imageview();

        steaming_card = (CardView)findViewById(R.id.steaming_card);
        grilling_card = (CardView)findViewById(R.id.grilling_card);
        roasting_card = (CardView)findViewById(R.id.roasting_card);
        boiling_card = (CardView)findViewById(R.id.boiling_card);
        stewing_card = (CardView)findViewById(R.id.stewing_card);
        frying_card = (CardView)findViewById(R.id.frying_card);
        deep_frying_card = (CardView)findViewById(R.id.deep_frying_card);
        other_card = (CardView)findViewById(R.id.other_card);

        loadResourceImage();
        categoryCardOnClick();
    }

    private void setCategory_imageview(){
        category_imageview[0] = Steaming;
        category_imageview[1] = Grilling;
        category_imageview[2] = Roasting;
        category_imageview[3] = Boiling;
        category_imageview[4] = Stewing;
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
        steaming_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToEachCategoryPage();
            }
        });

        grilling_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToEachCategoryPage();
            }
        });

        roasting_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToEachCategoryPage();
            }
        });

        boiling_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToEachCategoryPage();
            }
        });

        stewing_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToEachCategoryPage();
            }
        });

        frying_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToEachCategoryPage();
            }
        });

        deep_frying_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToEachCategoryPage();
            }
        });

        other_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToEachCategoryPage();
            }
        });
    }

    private void intentToEachCategoryPage(){
        Intent intent = new Intent(Category.this, CategoryPage.class);
        startActivity(intent);
    }
}
