package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    }
}