package com.example.notes;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class addActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    public static final String EXTRA_DATA_TITLE = "extra_notes_title";
    public static final String EXTRA_DATA_CONTENT = "extra_notes_content";
    private addviewModel maddviewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        EditText titleET = findViewById(R.id.titleET);
        EditText contentET = findViewById(R.id.contentET);

        final Bundle extras = getIntent().getExtras();
        maddviewModel = new ViewModelProvider(this).get(addviewModel.class);

        if (extras != null) {
            String notesTitle = extras.getString(EXTRA_DATA_TITLE, "");
            String notescontent = extras.getString(EXTRA_DATA_CONTENT, "");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
