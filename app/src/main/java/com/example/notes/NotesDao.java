package com.example.notes;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NotesDao {
    @Insert
    void insertNotes(Notes notes);

    @Update
    void updateNotes(Notes notes);

    @Delete
    void deleteNotes(Notes notes);

    @Query("Select * from notes")
    DataSource.Factory<Integer, Notes> getAllNotes();
}
