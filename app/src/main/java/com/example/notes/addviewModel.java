package com.example.notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

public class addviewModel extends AndroidViewModel {
    private NotesRepository notesRepository;
    LiveData<PagedList<Notes>> data;

    public addviewModel(@NonNull Application application) {
        super(application);
        notesRepository = NotesRepository.getNotesRepository(application);
        data = notesRepository.getAllNotes();
    }

    public void insertNotes(Notes notes) {
        notesRepository.insertNotes(notes);

    }

    public void updateNotes(Notes notes) {
        notesRepository.updateNotes(notes);
    }
}
