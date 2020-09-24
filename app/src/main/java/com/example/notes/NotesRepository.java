package com.example.notes;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.room.Dao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotesRepository {
    private NotesDao notesDao;
    private static NotesRepository notesRepository = null;
    private static int PAGE_SIZE = 15;
    public static ExecutorService executor= Executors.newSingleThreadExecutor();

    public NotesRepository(Application application) {
        NotesDatabase db = NotesDatabase.getINSTANCE(application);
        notesDao = db.notesDao();
    }
    public static NotesRepository getNotesRepository(Application application) {
        if(notesRepository==null) {
            synchronized (NotesRepository.class){
            if (notesRepository==null) {
                notesRepository = new NotesRepository(application);
            }

            }
        } return notesRepository;

    }
   public void insertNotes(final Notes notes) {
       executor.execute(new Runnable() {
           @Override
           public void run() {
               notesDao.insertNotes(notes);
           }
       });

    }

    public void updateNotes(final Notes notes) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                notesDao.updateNotes(notes);
            }
        });
    }

    public void deleteNotes(final Notes notes) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                notesDao.deleteNotes(notes);
            }
        });
    }

    public LiveData<PagedList<Notes>> getAllNotes() {
        return new LivePagedListBuilder<>(notesDao.getAllNotes(), PAGE_SIZE).build();
    }


}


