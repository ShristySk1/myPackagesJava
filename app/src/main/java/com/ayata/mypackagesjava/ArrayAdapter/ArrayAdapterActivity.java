package com.ayata.mypackagesjava.ArrayAdapter;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ayata.mypackagesjava.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ArrayAdapterActivity extends AppCompatActivity {
    ListView listView;
    List<Color> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter);
        initViews();
        addList();
        ColorsAdapter itemsAdapter =
                new ColorsAdapter(this, list);
        listView.setAdapter(itemsAdapter);
    }

    private void initViews() {
        listView = findViewById(R.id.list_colors);

    }
    private void addList(){
        list=new ArrayList<>();
        list.add(new Color("Black","#000000"));
        list.add(new Color("White","#ffffff"));
        list.add(new Color("Gray","#cccccc"));
        list.add(new Color("Red","#ff0000"));
    }
}
