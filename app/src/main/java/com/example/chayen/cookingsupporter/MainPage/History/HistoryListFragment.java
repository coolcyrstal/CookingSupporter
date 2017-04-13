package com.example.chayen.cookingsupporter.MainPage.History;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.MainPage.History.HistoryListFoodPage.HistoryFoodPage;
import com.example.chayen.cookingsupporter.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HistoryListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private ArrayList<FoodDatabaseClass> history_foodlist = new ArrayList<>();
    private String checkUser;

    private RecyclerView history_recyclerview;
    private HistoryAdapter historyAdapter;

    private ListView history_listview;
    ArrayList<String> history_foodlistname,history_foodlisttype, history_foodlistimage;
    private ArrayList<Long> history_foodlist_usercount, history_foodlist_star_count;
    private HistoryListAdapter historyListAdapter;
    public static FoodDatabaseClass history_foodpage_food;



//    private FragmentActivity fragmentActivity;
    FoodDatabaseClass food;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;


//    public HistoryListFragment() {
//        // Required empty public constructor
//    }

    public static HistoryListFragment newInstance() {
        HistoryListFragment fragment = new HistoryListFragment();
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
        View rootview = inflater.inflate(R.layout.fragment_history_list, container, false);
//        fragmentActivity = getActivity();
//        initlayoutManager(savedInstanceState);
        initialize(rootview);
        return rootview;
    }

    private void initlayoutManager(Bundle savedInstanceState){
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
    }

    private void initialize(View rootview){
//        history_recyclerview = (RecyclerView)rootview.findViewById(R.id.historyList);
        history_listview = (ListView)rootview.findViewById(R.id.historyList);
        historyListAdapter = new HistoryListAdapter();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            checkUser = user.getUid();
        }
        setHistoryFood(checkUser);

//        setHistoryAdapter();
//        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (history_recyclerview.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) history_recyclerview.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        history_recyclerview.setLayoutManager(mLayoutManager);
        history_recyclerview.scrollToPosition(scrollPosition);
    }

    private void setHistoryFood(String checkUser){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("food");
        final Query queryHistory = myRef.orderByChild("author").equalTo(checkUser);
//        Log.d("testhistory", "" + checkUser);
        queryHistory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                history_foodlist = new ArrayList<FoodDatabaseClass>();
                for(DataSnapshot messageSnapshot: dataSnapshot.getChildren()){
                    String food_image = (String) messageSnapshot.child("food_image").getValue();
                    String food_name = (String) messageSnapshot.child("food_name").getValue();
                    String food_type = (String) messageSnapshot.child("food_type").getValue();
                    String author = (String) messageSnapshot.child("author").getValue();
                    ArrayList<String> cooking_method = (ArrayList<String>) messageSnapshot.child("cooking_method").getValue();
                    ArrayList<String> ingredient = (ArrayList<String>) messageSnapshot.child("ingredient").getValue();
                    Long star_count = (Long) messageSnapshot.child("star_count").getValue();
                    Long user_count = (Long) messageSnapshot.child("user_count").getValue();
                    food = new FoodDatabaseClass();
                    food.setAuthor(author);
                    food.setCooking_method(cooking_method);
                    food.setFood_image(food_image);
                    food.setFood_name(food_name);
                    food.setFood_type(food_type);
                    food.setIngredient(ingredient);
                    food.setStar_count(star_count);
                    food.setUser_count(user_count);
                    history_foodlist.add(food);
//                    Log.d("testfoodhistory", "" + food_name + food_type);
                }
                history_foodlistname = new ArrayList<String>(history_foodlist.size());
                history_foodlisttype = new ArrayList<String>(history_foodlist.size());
                history_foodlistimage = new ArrayList<String>(history_foodlist.size());
                history_foodlist_usercount = new ArrayList<Long>(history_foodlist.size());
                history_foodlist_star_count = new ArrayList<Long>(history_foodlist.size());
                for(int i = 0; i < history_foodlist.size(); i++){
                    history_foodlistname.add(history_foodlist.get(i).getFood_name());
                    history_foodlisttype.add(history_foodlist.get(i).getFood_type());
                    history_foodlistimage.add(history_foodlist.get(i).getFood_image());
                    history_foodlist_usercount.add(history_foodlist.get(i).getUser_count());
                    history_foodlist_star_count.add(history_foodlist.get(i).getStar_count());
                }
                setHistoryAdapter();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void setHistoryAdapter(){
//        historyAdapter = new HistoryAdapter(history_foodlistname, history_foodlisttype, history_foodlistimage);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                historyAdapter = new HistoryAdapter(history_foodlist);
//                fragmentActivity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        history_recyclerview.setAdapter(historyAdapter);
//                    }
//                });
//            }
//        }).start();

//        historyAdapter.notifyDataSetChanged();
//        history_recyclerview.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));
//        history_recyclerview.setHasFixedSize(true);
//        historyAdapter = new HistoryAdapter(this.getActivity(), history_foodlist);
//        history_recyclerview.setAdapter(historyAdapter);

        historyListAdapter.setHistoryfood_name(history_foodlistname);
        historyListAdapter.setHistoryfood_type(history_foodlisttype);
        historyListAdapter.setHistoryfood_image(history_foodlistimage);
        historyListAdapter.setHistoryfood_usercount(history_foodlist_usercount);
        historyListAdapter.setHistoryfood_star_count(history_foodlist_star_count);
        history_listview.setAdapter(historyListAdapter);
        history_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), HistoryFoodPage.class);
                startActivity(intent);
                history_foodpage_food = history_foodlist.get(position);
            }
        });
    }

//    @Override
//    public void onResume(){
//        super.onResume();
//        ((HistoryAdapter) historyAdapter).setOnItemClickListener(new HistoryAdapter.MyClickListener() {
//            @Override
//            public void onItemClick(int position, View v) {
//
//            }
//        });
//    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
