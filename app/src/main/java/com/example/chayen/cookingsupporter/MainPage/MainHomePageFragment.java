package com.example.chayen.cookingsupporter.MainPage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodListAdapter;
import com.example.chayen.cookingsupporter.FoodListAdapter.FoodListView;
import com.example.chayen.cookingsupporter.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainHomePageFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    ListView listView;
    FoodListAdapter foodlist_adapter;

    public MainHomePageFragment() {
        // Required empty public constructor
    }

    public static MainHomePageFragment newInstance() {
        MainHomePageFragment fragment = new MainHomePageFragment();
        Bundle args = new Bundle();
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
        View rootview = inflater.inflate(R.layout.fragment_main_home_page, container, false);

        initInstance(rootview);
        return rootview;
    }

    private void initInstance(View rootview){
        String[] testfoodlist = new String[]{"1","2","3"};
        listView = (ListView)rootview.findViewById(R.id.foodList);
        //setFireBaseDatabase();
        foodlist_adapter = new FoodListAdapter();
        foodlist_adapter.setFood_name(testfoodlist);
        foodlist_adapter.setStar_value(testfoodlist);
        listView.setAdapter(foodlist_adapter);
    }

    private void setFireBaseDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
