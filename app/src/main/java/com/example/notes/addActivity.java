package com.example.notes;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class addActivity extends AppCompatActivity {

    public static final String EXTRA_DATA_ID = "extra_notes_id";
    public static final String EXTRA_DATA_TITLE = "extra_notes_title";
    public static final String EXTRA_DATA_CONTENT = "extra_notes_content";
    private addviewModel maddviewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        final EditText titleET = findViewById(R.id.titleET);
        final EditText contentET = findViewById(R.id.contentET);
        Button save = findViewById(R.id.newbutton);


        final Bundle extras = getIntent().getExtras();
        maddviewModel = new ViewModelProvider(this).get(addviewModel.class);

        if (extras != null) {
            String notesTitle = extras.getString(EXTRA_DATA_TITLE, "");
            String notescontent = extras.getString(EXTRA_DATA_CONTENT, "");
            Long notesid = extras.getLong(EXTRA_DATA_ID,0L);
            if (!notesTitle.isEmpty()) {
                titleET.setText(notesTitle);
            }
            if (!notescontent.isEmpty()){
                contentET.setText(notescontent);
            }
                save.setText("UPDATE");
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TITLE = titleET.getText().toString();
                String CONTENT = contentET.getText().toString();
            if (!TITLE.isEmpty()&& !CONTENT.isEmpty()) {
                if(extras!=null&& extras.containsKey(EXTRA_DATA_ID)){
                    long id = extras.getLong(EXTRA_DATA_ID);
                    Notes notes = new Notes ( id,TITLE,CONTENT);
                    maddviewModel.updateNotes(notes);
                }
                else {
                    Notes notes = new Notes (TITLE,CONTENT);
                    maddviewModel.insertNotes(notes);
                }

            }
            else {
                Toast.makeText(addActivity.this,"Missed inputs",Toast.LENGTH_SHORT).show();
            }
            setResult(RESULT_OK);
            finish();
            }
        });

    }

}
