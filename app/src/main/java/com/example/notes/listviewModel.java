package com.example.notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

public class listviewModel extends AndroidViewModel {
    private NotesRepository notesRepository;
    private LiveData<PagedList<Notes>> data;
    public listviewModel(@NonNull Application application) {
        super(application);
        notesRepository= NotesRepository.getNotesRepository(application);
        data =notesRepository.getAllNotes();
    }

        public void deleteNotes(Notes notes) {
        notesRepository.deleteNotes(notes);
        }
}
