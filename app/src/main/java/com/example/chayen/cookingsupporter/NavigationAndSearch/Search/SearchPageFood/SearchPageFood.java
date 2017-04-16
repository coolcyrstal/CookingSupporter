package com.example.chayen.cookingsupporter.NavigationAndSearch.Search.SearchPageFood;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
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

    private int user_count, star_count = 0;

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
        rootview.setFocusableInTouchMode(true);
        rootview.requestFocus();
        rootview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    getActivity().getSupportFragmentManager().popBackStack();
                    onSearchPageFoodBackPressed();
                    return true;
                }
                return false;
            }
        });
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

        user_count = searchpagefood.getUser_count().intValue();
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
                star_count = 1;
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
                star_count = 2;
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
                star_count = 3;
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
                star_count = 4;
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
                star_count = 5;
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

    private void onSearchPageFoodBackPressed(){
        if(star_count != 0){
            user_count++;
            star_count += searchpagefood.getStar_count();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference().child("food");
            Query query1 = myRef.orderByChild("food_name").equalTo(searchpagefood.getFood_name());
            query1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        if(snapshot.child("food_image").getValue().toString().equals(searchpagefood.getFood_image())){
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
        }
    }
}
