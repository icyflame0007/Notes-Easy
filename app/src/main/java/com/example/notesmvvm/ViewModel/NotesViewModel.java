package com.example.notesmvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notesmvvm.Model.Notes;
import com.example.notesmvvm.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository repository;

    public LiveData<List<Notes>> getAllNotes;
    public NotesViewModel(@NonNull Application application) {
        super(application);

        repository = new NotesRepository(application);
        getAllNotes = repository.getAllNotes;
    }

    public void insertNote(Notes notes)
    {
        repository.insertNotes(notes);
    }

    public void updateNote(Notes notes)
    {
        repository.updateNotes(notes);
    }

    public void deleteNote(int id)
    {
        repository.deleteNotes(id);
    }
}
