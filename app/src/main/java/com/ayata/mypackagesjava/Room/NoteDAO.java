package com.ayata.mypackagesjava.Room;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoteDAO {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("select * from note_table")
    LiveData<List<Note>> getAllNotes();

    @Query("delete from note_table")
    void deleteAllNotes();
}
