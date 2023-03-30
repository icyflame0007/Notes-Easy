package com.example.notesmvvm.Adapter;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesmvvm.Activity.UpdateNoteActivity;
import com.example.notesmvvm.MainActivity;
import com.example.notesmvvm.Model.Notes;
import com.example.notesmvvm.R;
import com.example.notesmvvm.ViewModel.NotesViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    MainActivity mainActivity;
    List<Notes> notes;
    NotesViewModel notesViewModel;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes  = notes;
    }

    public void searchNotes(List<Notes> filteredNotes)
    {
        this.notes = filteredNotes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView notestitle,notessubtitle,notesdate;
        View notespriority;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notestitle = itemView.findViewById(R.id.notetitle);
            notessubtitle = itemView.findViewById(R.id.notessubtitle);
            notesdate = itemView.findViewById(R.id.notesdate);
            notespriority = itemView.findViewById(R.id.notespriority);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.notes_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notes note = notes.get(position);
        holder.notestitle.setText(note.notesTitle);
        holder.notessubtitle.setText(note.notesSubtitle);
        holder.notesdate.setText(note.notesDate);

        notesViewModel = new ViewModelProvider(mainActivity).get(NotesViewModel.class);
        if(note.notesPriority.equals("1"))

        {
          holder.notespriority.setBackgroundResource(R.drawable.green_circle);

        }
        else if(note.notesPriority.equals("2"))
        {
            holder.notespriority.setBackgroundResource(R.drawable.yellow_circle);
        }
        else if(note.notesPriority.equals("3"))
        {
            holder.notespriority.setBackgroundResource(R.drawable.red_circle);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(mainActivity, UpdateNoteActivity.class);
              intent.putExtra("id",note.id);
              intent.putExtra("title",note.notesTitle);
              intent.putExtra("subtitle",note.notesSubtitle);
              intent.putExtra("priority",note.notesPriority);
              intent.putExtra("notes",note.notes);
              mainActivity.startActivity(intent);
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                PopupMenu popupMenu = new PopupMenu(mainActivity,v);
                popupMenu.inflate(R.menu.menu);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    popupMenu.setGravity(0);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    popupMenu.setForceShowIcon(true);
                }
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.popup_edit)
                        {

                            Intent intent = new Intent(mainActivity, UpdateNoteActivity.class);
                            intent.putExtra("id",note.id);
                            intent.putExtra("title",note.notesTitle);
                            intent.putExtra("subtitle",note.notesSubtitle);
                            intent.putExtra("priority",note.notesPriority);
                            intent.putExtra("notes",note.notes);
                            mainActivity.startActivity(intent);

                        }
                        else if(item.getItemId() == R.id.popup_delete)
                        {

                            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mainActivity,R.style.BottomSheetDialogStyle);

                            View view = LayoutInflater.from(mainActivity).inflate(R.layout.delete_bottomsheet,(LinearLayout)holder.itemView.findViewById(R.id.bottom_sheet));
                            bottomSheetDialog.setContentView(view);

                            bottomSheetDialog.show();
                            TextView yes,no;
                            no = view.findViewById(R.id.bottom_no);
                            yes = view.findViewById(R.id.bottom_yes);
                            no.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    bottomSheetDialog.dismiss();
                                }
                            });

                            yes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    notesViewModel.deleteNote(note.id);
                                    bottomSheetDialog.dismiss();

                                }
                            });

                        }
                        return true;
                    }
                });
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return notes.size();
    }


}
