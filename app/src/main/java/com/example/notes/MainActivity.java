package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addbtn = findViewById(R.id.add);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,addActivity.class);
                startActivity(intent);
            }
        });
        viewModel = new ViewModelProvider(this).get(listviewModel.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final NotesPaginglistAdapter notesPaginglistAdapter = new NotesPaginglistAdapter();

        recyclerView.setAdapter(notesPaginglistAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        final Snackbar snackbar = Snackbar.make(constraintLayout,"Task Deleted ", BaseTransientBottomBar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.insertNotes(notes);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                    notes = notesPaginglistAdapter.getNotesAtPosition(pos);
                    viewModel.deleteNotes(notes);
                    snackbar.show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}