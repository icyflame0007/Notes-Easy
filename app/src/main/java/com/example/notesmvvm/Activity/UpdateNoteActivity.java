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
    ImageView greenpriority,yellowpriority,redpriority;
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
        greenpriority = findViewById(R.id.greenpriority);
        yellowpriority = findViewById(R.id.yellowpriority);
        redpriority = findViewById(R.id.redpriority);

        updt_title.setText(stitle);
        updt_subtitle.setText(ssubtitle);
        updt_notes.setText(snotes);
        if(spriority.equals("1"))
        {
           greenpriority.setImageResource(R.drawable.done);

        }
        else if(spriority.equals("2"))
        {
            yellowpriority.setImageResource(R.drawable.done);
        }
        else if(spriority.equals("3"))
        {
            redpriority.setImageResource(R.drawable.done);

        }
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        greenpriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                greenpriority.setImageResource(R.drawable.done);
                yellowpriority.setImageResource(0);
                redpriority.setImageResource(0);
                priority = "1";

            }
        });
        yellowpriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                greenpriority.setImageResource(0);
                yellowpriority.setImageResource(R.drawable.done);
                redpriority.setImageResource(0);
                priority = "2";

            }
        });
        redpriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                greenpriority.setImageResource(0);
                yellowpriority.setImageResource(0);
                redpriority.setImageResource(R.drawable.done);
                priority = "3";

            }
        });

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
        updatenotes.notesPriority = priority;
       notesViewModel.updateNote(updatenotes);
    }
}