package com.ayata.mypackagesjava.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ayata.mypackagesjava.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
/*
For ViewModel providers,we need lifecycle dependency as well
put Room dependencies
For rename=select word then shift +F6
*/

public class NoteActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton btnAdd;
    int count = 0;
    ListView notelistView;
    ArrayAdapter<Note> arrayAdapter;
    List<Note> listnotes;


    private String TAG = "NoteActivity";
    private NoteViewModel noteviewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        initViews();
        btnAdd.setOnClickListener(this);
        //needed part started
        noteviewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteviewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                listnotes.clear();
                listnotes.addAll(notes);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        //needed part ended
        notelistView.setAdapter(arrayAdapter);
    }

    private void initViews() {
        btnAdd = findViewById(R.id.btn_add);
        notelistView = findViewById(R.id.note_listview);
        listnotes = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<Note>(this, R.layout.recycle_notelist, R.id.title, listnotes);
//        noteviewModel.insert(new Note("title already", "description"));

    }

    @Override
    public void onClick(View v) {
        noteviewModel.insert(new Note("title" + 1, "description" + 1));
    }
}
