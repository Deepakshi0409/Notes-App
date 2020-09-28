package com.example.notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class addviewModel extends AndroidViewModel {
    private NotesRepository notesRepository;
    public addviewModel(@NonNull Application application) {
        super(application);
        notesRepository = new NotesRepository(application);
    }
    public void insertNotes(Notes notes){
        notesRepository.insertNotes(notes);

    }
    public void updateNotes(Notes notes) {
        notesRepository.updateNotes(notes);
    }
}
