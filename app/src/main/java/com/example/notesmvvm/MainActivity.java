package com.example.notesmvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.notesmvvm.Activity.InsertNoteActivity;
import com.example.notesmvvm.Adapter.NotesAdapter;
import com.example.notesmvvm.Model.Notes;
import com.example.notesmvvm.Repository.NotesRepository;
import com.example.notesmvvm.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton create_note;
    RecyclerView notesRecycler;
    NotesAdapter adapter;
    EditText searchView;
    List<Notes> allNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0.0f);
        create_note = findViewById(R.id.create_note);
        notesRecycler = findViewById(R.id.notesRecycler);
        searchView = findViewById(R.id.search_edittext);
        NotesViewModel notesViewModel;

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                NotesFilter(String.valueOf(s));
            }
        });

        create_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InsertNoteActivity.class));
            }
        });
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
                allNotes = notes;

            }
        });



    }

    private void NotesFilter(String newText) {

        ArrayList<Notes> filternames = new ArrayList<>();

        for(Notes notes:this.allNotes )
        {
             if(notes.notesTitle.contains(newText) || notes.notesSubtitle.contains(newText))
             {
                 filternames.add(notes);
             }
        }
        this.adapter.searchNotes(filternames);

    }

    public void setAdapter(List<Notes> notes)
    {
        notesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new NotesAdapter(MainActivity.this,notes);
        notesRecycler.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        NotesViewModel notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class); ;
        if(item.getItemId() == R.id.newest)
        {
            Toast.makeText(this, "Newest first", Toast.LENGTH_SHORT).show();
            notesViewModel.newestfirst.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    allNotes = notes;
                }
            });
        }

        else if(item.getItemId() == R.id.oldest)
        {

            Toast.makeText(this, "Oldest First", Toast.LENGTH_SHORT).show();
            notesViewModel.oldestfirst.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    allNotes = notes;

                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}