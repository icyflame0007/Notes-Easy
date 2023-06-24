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

public class UpdateNoteActivity extends AppCompatActivity {

    EditText updt_title,updt_subtitle,updt_notes;
    FloatingActionButton updt_done;
    String priority = "1";
    String stitle,ssubtitle,snotes,spriority;
    int iid;
    NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        iid = getIntent().getIntExtra("id",0);
        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        spriority = getIntent().getStringExtra("priority");
        snotes = getIntent().getStringExtra("notes");

        updt_title = findViewById(R.id.updt_title);
        updt_subtitle = findViewById(R.id.updt_subtitle);
        updt_notes = findViewById(R.id.updt_notes);
        updt_done = findViewById(R.id.updt_done);

        updt_title.setText(stitle);
        updt_subtitle.setText(ssubtitle);
        updt_notes.setText(snotes);

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);


        updt_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = updt_title.getText().toString().trim();
                String subtitle = updt_subtitle.getText().toString().trim();
                String notes = updt_notes.getText().toString().trim();
                UpdateNotes(title,subtitle,notes);
                Toast.makeText(UpdateNoteActivity.this, "Notes Updated", Toast.LENGTH_SHORT).show();
                finish();

            }
        });


    }

    private void UpdateNotes(String title, String subtitle, String notes) {
        Date date = new Date();
        CharSequence format = DateFormat.getDateInstance().format(date.getTime());
        Notes updatenotes = new Notes();
        updatenotes.notesTitle = title;
        updatenotes.id = iid;
        updatenotes.notesSubtitle =  subtitle;
        updatenotes.notes = notes;
        updatenotes.notesDate = (String)format;
       notesViewModel.updateNote(updatenotes);
    }
}