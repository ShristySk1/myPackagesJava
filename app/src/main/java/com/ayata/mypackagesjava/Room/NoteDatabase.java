package com.ayata.mypackagesjava.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    static NoteDatabase database;
    abstract NoteDAO noteDAO();

    public static NoteDatabase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(
                    context.getApplicationContext()
                    , NoteDatabase.class
                    , "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
}
