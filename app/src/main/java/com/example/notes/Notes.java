package com.example.notes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Notes {

@PrimaryKey(autoGenerate = true)
    private long id;
@ColumnInfo(name ="Title")
    private String title;

@ColumnInfo(name = "Content")
    private String content;

public Notes(long id, String title, String content) {
   this.id=id;
   this.title = title;
   this.content=content;
    }

    @Ignore
    public Notes(String title, String content){
    this.title = title;
    this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isNotesEqual(Notes n2) {
    return (title.equals(n2.getTitle())&&(content.equals(n2.getContent())));
    }
}
