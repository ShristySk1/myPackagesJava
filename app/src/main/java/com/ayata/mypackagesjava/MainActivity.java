package com.ayata.mypackagesjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.ayata.mypackagesjava.Room.Note;
import com.ayata.mypackagesjava.Room.NoteViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
NoteViewModel noteviewModel;
private String TAG="MainActivity";
private List<Note> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteviewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

         list=new ArrayList<>();
        list=noteviewModel.getAllNotes().getValue();
        if(list==null){
            Log.d(TAG, "onCreate: empty list");
        }else{
            Log.d(TAG, "onCreate: size="+list.size());
        }
        noteviewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                Log.d(TAG, "onChanged: ");
                for(Note note:notes){
                    Log.d(TAG, "onChanged: "+note.getTitle());
                }
            }
        });

    }
}
