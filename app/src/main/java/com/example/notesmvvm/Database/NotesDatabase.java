package com.example.notesmvvm.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;

import com.example.notesmvvm.Dao.NotesDao;
import com.example.notesmvvm.Model.Notes;

@Database(entities = {Notes.class},version = 2)
public abstract class NotesDatabase extends RoomDatabase {




    public  abstract NotesDao notesDao();

    public static NotesDatabase INSTANCE;



    public static NotesDatabase getDatabaseInstance(Context context){

        if(INSTANCE == null)
        {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),NotesDatabase.class,"Notes_DB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }
        return INSTANCE;
    }


}
