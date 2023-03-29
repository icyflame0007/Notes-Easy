package com.example.notesmvvm.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notesmvvm.Model.Notes;

import java.util.List;

@Dao
public interface NotesDao {
    @Query("Select * from Notes_DB")
    LiveData<List<Notes>> getAllNotes();

    @Insert
    void insertNotes(Notes... notes);

    @Query("Delete From Notes_DB where id =:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);

}
