package com.ayata.mypackagesjava.Room;


import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepository {
    private String TAG = "NoteRepository";
    private NoteDatabase database;
    private NoteDAO dao;
    private LiveData<List<Note>> list;

    NoteRepository(Application application) {
        database = NoteDatabase.getInstance(application);
        dao = database.noteDAO();
    }

    public void insert(Note note) {
        new NoteInsert(dao).execute(note);
    }

    public void delete(Note note) {
        new NoteDelete(dao).execute(note);
    }

    public LiveData<List<Note>> getAllNotes() {
        return dao.getAllNotes();
    }

    private class NoteInsert extends AsyncTask<Note, Void, String> {
        NoteDAO dao;

        NoteInsert(NoteDAO dao) {
            this.dao = dao;
        }

        @Override
        protected String doInBackground(Note... notes) {
            dao.insert(notes[0]);
            return "note inserted successfully";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "onPostExecute: " + result);
        }
    }

    private class NoteDelete extends AsyncTask<Note, Void, String> {
        NoteDAO dao;

        NoteDelete(NoteDAO dao) {
            this.dao = dao;
        }

        @Override
        protected String doInBackground(Note... notes) {
            dao.delete(notes[0]);
            return "note deleted successfully";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "onPostExecute: " + result);
        }
    }
}
