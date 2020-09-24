package com.ayata.mypackagesjava.AutoCompleteSearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ayata.mypackagesjava.R;

public class SearchFragmentContainer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityfragmentcontainer_search);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new SearchFragment()).addToBackStack(null).commit();
    }
}