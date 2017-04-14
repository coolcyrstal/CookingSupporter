package com.example.chayen.cookingsupporter.NavigationAndSearch.Search.SearchPageFood;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.R;
import com.squareup.picasso.Picasso;


public class SearchPageFood extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static FoodDatabaseClass searchpagefood;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView searchpagefood_image;
    private TextView searchpagefood_name, searchpagefood_type;
    private Button searchpage_foodpage_starbutton_1, searchpage_foodpage_starbutton_2,
            searchpage_foodpage_starbutton_3, searchpage_foodpage_starbutton_4, searchpage_foodpage_starbutton_5;

    public SearchPageFood() {
        // Required empty public constructor
    }


    public static SearchPageFood newInstance(FoodDatabaseClass myDataset) {
        SearchPageFood fragment = new SearchPageFood();
        Bundle args = new Bundle();
        searchpagefood = myDataset;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_search_page_food, container, false);

        initialize(rootview);
        setViewPager();
        return rootview;
    }

    private void initialize(View rootview){
        searchpagefood_image = (ImageView)rootview.findViewById(R.id.searchpage_cooking_recipe_foodimage);
        searchpagefood_name = (TextView)rootview.findViewById(R.id.searchpage_cooking_recipe_foodname);
        searchpagefood_type = (TextView)rootview.findViewById(R.id.searchpage_cooking_recipe_foodtype);
        searchpage_foodpage_starbutton_1 = (Button)rootview.findViewById(R.id.search_star_button_1);
        searchpage_foodpage_starbutton_2 = (Button)rootview.findViewById(R.id.search_star_button_2);
        searchpage_foodpage_starbutton_3 = (Button)rootview.findViewById(R.id.search_star_button_3);
        searchpage_foodpage_starbutton_4 = (Button)rootview.findViewById(R.id.search_star_button_4);
        searchpage_foodpage_starbutton_5 = (Button)rootview.findViewById(R.id.search_star_button_5);
        viewPager = (ViewPager) rootview.findViewById(R.id.viewPager_searchpage_foodpage);
        tabLayout = (TabLayout) rootview.findViewById(R.id.tabLayout_searchpage_foodpage);

        Picasso.with(getContext()).load(searchpagefood.getFood_image()).into(searchpagefood_image);
        searchpagefood_name.setText(searchpagefood.getFood_name());
        searchpagefood_type.setText(searchpagefood.getFood_type());

        search_starbuttonOnclick();
    }

    private void search_starbuttonOnclick(){
        searchpage_foodpage_starbutton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchpage_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                searchpage_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button);
                searchpage_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button);
                searchpage_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button);
                searchpage_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button);
            }
        });

        searchpage_foodpage_starbutton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchpage_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                searchpage_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button_fill);
                searchpage_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button);
                searchpage_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button);
                searchpage_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button);
            }
        });

        searchpage_foodpage_starbutton_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchpage_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                searchpage_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button_fill);
                searchpage_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button_fill);
                searchpage_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button);
                searchpage_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button);
            }
        });

        searchpage_foodpage_starbutton_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchpage_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                searchpage_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button_fill);
                searchpage_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button_fill);
                searchpage_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button_fill);
                searchpage_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button);
            }
        });

        searchpage_foodpage_starbutton_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchpage_foodpage_starbutton_1.setBackgroundResource(R.drawable.star_button_fill);
                searchpage_foodpage_starbutton_2.setBackgroundResource(R.drawable.star_button_fill);
                searchpage_foodpage_starbutton_3.setBackgroundResource(R.drawable.star_button_fill);
                searchpage_foodpage_starbutton_4.setBackgroundResource(R.drawable.star_button_fill);
                searchpage_foodpage_starbutton_5.setBackgroundResource(R.drawable.star_button_fill);
            }
        });
    }

    private void setViewPager() {
        viewPager.setAdapter(new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return SearchPageFoodIngredient.newInstance(searchpagefood.getIngredient());
                    case 1:
                        return SearchPageFoodCookingMethod.newInstance(searchpagefood.getCooking_method());
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

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
