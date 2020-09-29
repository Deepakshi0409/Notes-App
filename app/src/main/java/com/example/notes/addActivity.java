package com.example.notes;

import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

public class addActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_DATA_NAME = "extra_notes_name";
    public static final String EXTRA_DATA_CONTENT = "extra_notes_content";
    private addviewModel maddviewModel;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
