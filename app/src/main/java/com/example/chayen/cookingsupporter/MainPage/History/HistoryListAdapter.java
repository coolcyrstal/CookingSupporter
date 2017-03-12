package com.example.chayen.cookingsupporter.MainPage.History;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by chayen on 12-Mar-17.
 */

public class HistoryListAdapter extends BaseAdapter{

    private ArrayList<String> historyfood_name, historyfood_type, historyfood_image;


    @Override
    public int getCount() {
        if (historyfood_name == null)
            return 0;
        else
            return historyfood_name.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        HistoryListView historyListView = new HistoryListView(viewGroup.getContext());
        historyListView.setHistoryfood_name(historyfood_name.get(position));
        historyListView.setHistoryfood_type(historyfood_type.get(position));
        historyListView.setHistoryfood_image(historyfood_image.get(position));
        return historyListView;
    }

    public void setHistoryfood_name(ArrayList<String> historyfood_name) {
        this.historyfood_name = historyfood_name;
    }

    public void setHistoryfood_type(ArrayList<String> historyfood_type) {
        this.historyfood_type = historyfood_type;
    }

    public void setHistoryfood_image(ArrayList<String> historyfood_image) {
        this.historyfood_image = historyfood_image;
    }
}
