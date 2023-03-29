package com.example.notesmvvm.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notesmvvm.Dao.NotesDao;
import com.example.notesmvvm.Database.NotesDatabase;
import com.example.notesmvvm.Model.Notes;

import java.util.List;

public class NotesRepository {


    public NotesDao notesDao;
    public LiveData<List<Notes>> getAllNotes;

    public NotesRepository(Application application)
    {
        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);

        notesDao = database.notesDao();
        getAllNotes = notesDao.getAllNotes();
    }

    public void insertNotes(Notes notes)
    {
        notesDao.insertNotes(notes);
    }

    public void updateNotes(Notes notes)
    {
        notesDao.updateNotes(notes);
    }
    public void deleteNotes(int id)
    {
        notesDao.deleteNotes(id);
    }
}
