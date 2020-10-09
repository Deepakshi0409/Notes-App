package com.example.notes;

import android.content.Context;
import android.content.res.AssetManager;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Notes.class}, version = 1, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NotesDao notesDao();
    public static ExecutorService executor= Executors.newSingleThreadExecutor();

    public static NotesDatabase INSTANCE = null;

    public static NotesDatabase getINSTANCE(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, NotesDatabase.class, "notes_database").addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            executor.execute(new Runnable() {
                                @Override
                                public void run() {
                                prepopulateDB(context.getAssets(),INSTANCE.notesDao());
                                }
                            });
                        }
                    }).fallbackToDestructiveMigration().build();
                }
            }

        }
        return INSTANCE;
    }
        private static void prepopulateDB(AssetManager assetManager, NotesDao notesDao){
            BufferedReader bufferedReader = null;
            StringBuilder stringBuilder = new StringBuilder();
            String json = "";

            try {
                bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open("myNotes.json")));
                String mLine;
                while ((mLine = bufferedReader.readLine())!=null){
                    stringBuilder.append(mLine);
                }
                json=stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (bufferedReader!=null){
                    try{
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray Notes = jsonObject.getJSONArray("myNotes");
                for (int i=0; i<Notes.length();i++){
                    JSONObject myNotes = Notes.getJSONObject(i);
                    String title = myNotes.getString("title");
                    String content = myNotes.getString("content");
                    Notes notes = new Notes(0L,title,content);
                    notesDao.insertNotes(notes);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
}
