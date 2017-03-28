package com.example.chayen.cookingsupporter.NavigationAndSearch.Search;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chayen.cookingsupporter.FoodListAdapter.FoodDatabaseClass;
import com.example.chayen.cookingsupporter.NavigationAndSearch.Search.SearchPageFood.SearchPageFood;
import com.example.chayen.cookingsupporter.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by chayen on 28-Mar-17.
 */

public class SearchPageAdapter extends RecyclerView.Adapter<SearchPageAdapter.SearchPageViewHolder>{

    private Context context;
    private ArrayList<FoodDatabaseClass> searchfood_list;
    private FoodDatabaseClass food;

    @Override
    public SearchPageAdapter.SearchPageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_search_food, parent, false);
        SearchPageViewHolder dataObjHolder = new SearchPageViewHolder(view);
        context = parent.getContext();
        return dataObjHolder;
    }

    @Override
    public void onBindViewHolder(SearchPageAdapter.SearchPageViewHolder holder, final int position) {
        Picasso.with(context).load(searchfood_list.get(position).getFood_image()).into(holder.searchpage_foodimage);
        holder.searchpage_foodname.setText(searchfood_list.get(position).getFood_name());
        holder.searchpage_foodtype.setText(searchfood_list.get(position).getFood_type());
        holder.searchpage_foodcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = searchfood_list.get(position);
//                Toast.makeText(v.getContext(), "click", Toast.LENGTH_SHORT).show();
                Fragment fragment = ((FragmentActivity) context).getSupportFragmentManager().findFragmentById(R.id.searchactivity_id);
                if (fragment instanceof SearchPageFood == false) {
                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.searchactivity_id, SearchPageFood.newInstance(food), "search Success")
                            .addToBackStack(null)
                            .commit();
                }else Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchfood_list.size();
    }

    SearchPageAdapter(ArrayList<FoodDatabaseClass> myDataset){
        searchfood_list = myDataset;
    }

    public class SearchPageViewHolder extends RecyclerView.ViewHolder {

        private CardView searchpage_foodcard;
        private TextView searchpage_foodname, searchpage_foodtype;
        private ImageView searchpage_foodimage;

        public SearchPageViewHolder(View itemView) {
            super(itemView);
            searchpage_foodcard = (CardView)itemView.findViewById(R.id.searchfood_adapter_card);
            searchpage_foodimage = (ImageView)itemView.findViewById(R.id.searchfood_list_image);
            searchpage_foodname = (TextView)itemView.findViewById(R.id.searchfood_list_name);
            searchpage_foodtype = (TextView)itemView.findViewById(R.id.searchfood_list_type);
        }
    }
}
