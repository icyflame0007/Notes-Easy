package com.example.notesmvvm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.notesmvvm.Model.Notes;
import com.example.notesmvvm.R;
import com.example.notesmvvm.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.Date;

public class InsertNoteActivity extends AppCompatActivity {
    EditText insrt_title,insrt_subtitle,insrt_notes;
    FloatingActionButton insrt_done;
    NotesViewModel notesViewModel;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_note);


        insrt_title = findViewById(R.id.insrt_title);
        insrt_subtitle = findViewById(R.id.insrt_subtitle);
        insrt_notes = findViewById(R.id.insrt_notes);
        insrt_done = findViewById(R.id.insrt_done);

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);





        insrt_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = insrt_title.getText().toString().trim();
                String subtitle = insrt_subtitle.getText().toString().trim();
                String notes = insrt_notes.getText().toString().trim();
                CreateNotes(title,subtitle,notes);
                Toast.makeText(InsertNoteActivity.this, "Notes Created", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void CreateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence format = DateFormat.getDateInstance().format(date.getTime());
        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubtitle =  subtitle;
        notes1.notes = notes;
        notes1.notesDate = (String)format;
        notesViewModel.insertNote(notes1);

    }
}