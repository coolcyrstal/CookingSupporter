package com.example.chayen.cookingsupporter.NavigationAndSearch.Search;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chayen.cookingsupporter.R;


public class SearchPage extends Fragment {

    private OnFragmentInteractionListener mListener;

    public SearchPage() {
        // Required empty public constructor
    }


    public static SearchPage newInstance() {
        SearchPage fragment = new SearchPage();
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
        return inflater.inflate(R.layout.fragment_search_page, container, false);
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
