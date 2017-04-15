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
    private int[] image_star_rank = {
            R.drawable.rsz_star_0,
            R.drawable.rsz_star_0_5,
            R.drawable.rsz_star_1,
            R.drawable.rsz_star_1_5,
            R.drawable.rsz_star_2,
            R.drawable.rsz_star_2_5,
            R.drawable.rsz_star_3,
            R.drawable.rsz_star_3_5,
            R.drawable.rsz_star_4,
            R.drawable.rsz_star_4_5,
            R.drawable.rsz_star_5
    };

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
        Picasso.with(context).load(foodrank(position)).into(holder.searchpage_foodrank);
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

    private int foodrank(int position){
        Double user_count, star_count, star_value;
        user_count = searchfood_list.get(position).getUser_count().doubleValue();
        star_count = searchfood_list.get(position).getStar_count().doubleValue();
        if(user_count == 0 || star_count == 0){
            return image_star_rank[0];
        }
        else{
            star_value = star_count/user_count;
            if(star_value <= 0.5){
                return image_star_rank[1];
            } else if(star_value > 0.5 && star_value <= 1){
                return image_star_rank[2];
            } else if(star_value > 1 && star_value <= 1.5){
                return image_star_rank[3];
            } else if(star_value > 1.5 && star_value <= 2){
                return image_star_rank[4];
            } else if(star_value > 2 && star_value <= 2.5){
                return image_star_rank[5];
            } else if(star_value > 2.5 && star_value <= 3){
                return image_star_rank[6];
            } else if(star_value > 3 && star_value <= 3.5){
                return image_star_rank[7];
            } else if(star_value > 3.5 && star_value <= 4){
                return image_star_rank[8];
            } else if(star_value > 4 && star_value <= 4.5){
                return image_star_rank[9];
            } else {
                return image_star_rank[10];
            }
        }
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
        private ImageView searchpage_foodrank;

        public SearchPageViewHolder(View itemView) {
            super(itemView);
            searchpage_foodcard = (CardView)itemView.findViewById(R.id.searchfood_adapter_card);
            searchpage_foodimage = (ImageView)itemView.findViewById(R.id.searchfood_list_image);
            searchpage_foodname = (TextView)itemView.findViewById(R.id.searchfood_list_name);
            searchpage_foodtype = (TextView)itemView.findViewById(R.id.searchfood_list_type);
            searchpage_foodrank = (ImageView)itemView.findViewById(R.id.searchfood_list_rank);
        }
    }
}
