package com.example.chayen.cookingsupporter.NavigationAndSearch.Search;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chayen.cookingsupporter.R;

public class Search extends AppCompatActivity {

    private EditText searchpage_foodname_edittext, searchpage_ingredient_edittext;
    private Button searchpage_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initialize();
    }

    private void initialize(){
        searchpage_foodname_edittext = (EditText)findViewById(R.id.searchpage_foodname);
        searchpage_ingredient_edittext = (EditText)findViewById(R.id.searchpage_foodingredient);
        searchpage_button = (Button)findViewById(R.id.searchpage_button);

        searchpage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToSearchPage();
            }
        });
    }

    private void intentToSearchPage(){
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.searchactivity_id);
        if (fragment instanceof SearchPage == false) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.searchactivity_id,
                            SearchPage.newInstance(searchpage_foodname_edittext.getText().toString(),
                                    searchpage_ingredient_edittext.getText().toString()),
                            "Search Success")
                    .addToBackStack(null)
                    .commit();
        }else Toast.makeText(Search.this, "Error", Toast.LENGTH_SHORT).show();
    }
}
