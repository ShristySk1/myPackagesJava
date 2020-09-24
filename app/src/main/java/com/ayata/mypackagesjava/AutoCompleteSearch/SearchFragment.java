package com.ayata.mypackagesjava.AutoCompleteSearch;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ayata.mypackagesjava.R;
import com.google.android.material.textfield.TextInputLayout;

public class SearchFragment extends Fragment {

LinearLayout search_layout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);
        search_layout=view.findViewById(R.id.linearLayout_search);
        search_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),SearchActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}