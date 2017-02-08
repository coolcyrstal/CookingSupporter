package com.example.chayen.cookingsupporter.NavigationAndSearch;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.chayen.cookingsupporter.R;

public class AddFoodMenu extends AppCompatActivity {

    LinearLayout ingredient_layout, cookingmethod_layout;
    EditText ingredient_edittext, cookingmethod_edittext;
    FloatingActionButton addingredient, addcookingmethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_menu);

        initialize();
    }

    private void initialize(){
        ingredient_layout = (LinearLayout) findViewById(R.id.layout_addedittext_ingredient);
        cookingmethod_layout = (LinearLayout) findViewById(R.id.layout_addedittext_cookingmethod);
        ingredient_edittext = (EditText)findViewById(R.id.text_addfood_ingredient);
        cookingmethod_edittext = (EditText)findViewById(R.id.text_addfood_cookingmethod);

        addingredient = (FloatingActionButton)findViewById(R.id.ingredient_plus_edittext_button);
        addcookingmethod = (FloatingActionButton) findViewById(R.id.cookingmethod_plus_edittext_button);

        addingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEditText(ingredient_layout, "ส่วนผสม");
            }
        });

        addcookingmethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEditText(cookingmethod_layout, "ขั้นตอนการทำ");
            }
        });
    }

    private void createEditText(LinearLayout layout, String text){
        EditText editTextOne = new EditText(this);
        editTextOne.setHint(text);
        editTextOne.setHintTextColor(Color.CYAN);
        layout.addView(editTextOne);
    }
}
