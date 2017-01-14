package com.example.chayen.cookingsupporter.CreateAccount;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.chayen.cookingsupporter.R;


public class RegisterSuccess extends Fragment {

    public RegisterSuccess() {
        // Required empty public constructor
    }


    public static RegisterSuccess newInstance() {
        RegisterSuccess fragment = new RegisterSuccess();
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
        View view = inflater.inflate(R.layout.fragment_register_success, container, false);
        Button start = (Button)view.findViewById(R.id.let_start_button);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }
}
