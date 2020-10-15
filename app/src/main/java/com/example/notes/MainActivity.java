package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private listviewModel viewModel;
    private Notes notes;
    public static final int NEW_DATA_REQUEST_CODE = 1;
    public static final int UPDATE_DATA_REQUEST_CODE = 2;
    public static final String EXTRA_DATA_ID = "extra_notes_id";
    public static final String EXTRA_DATA_TITLE = "extra_notes_title";
    public static final String EXTRA_DATA_CONTENT = "extra_notes_content";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addbtn = findViewById(R.id.floatingActionButton);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addActivity.class);
                startActivityForResult(intent, NEW_DATA_REQUEST_CODE);
            }
        });
        viewModel = new ViewModelProvider(this).get(listviewModel.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final NotesPaginglistAdapter noteslistPagingAdapter = new NotesPaginglistAdapter();
        recyclerView.setAdapter(noteslistPagingAdapter);

        viewModel.LiveData.observe(this, new Observer<PagedList<Notes>>() {
            @Override
            public void onChanged(PagedList<Notes> notes) {
                noteslistPagingAdapter.submitList(notes);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        final Snackbar snackbar = Snackbar.make(constraintLayout, "Notes Deleted ", BaseTransientBottomBar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewModel.insertNotes(notes);
                    }
                });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                notes = noteslistPagingAdapter.getNotesAtPosition(pos);
                viewModel.deleteNotes(notes);
                snackbar.show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
        noteslistPagingAdapter.setonItemClickListener(new NotesPaginglistAdapter.ClickListener() {
            @Override
            public void OnitemClick(int position, View view) {
                Notes currentNotes = noteslistPagingAdapter.getNotesAtPosition(position);
                launchUpdateNotes(currentNotes);
            }
        });


    }

    private void launchUpdateNotes(Notes notes) {
        Intent intent = new Intent(MainActivity.this, addActivity.class);
        intent.putExtra(EXTRA_DATA_ID, notes.getId());
        intent.putExtra(EXTRA_DATA_TITLE, notes.getTitle());
        intent.putExtra(EXTRA_DATA_CONTENT, notes.getContent());
        startActivityForResult(intent, UPDATE_DATA_REQUEST_CODE);
    }
}