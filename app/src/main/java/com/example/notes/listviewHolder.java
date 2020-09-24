package com.example.notes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class listviewHolder extends RecyclerView.ViewHolder {
    TextView titleTV, contentTV;
    public listviewHolder(@NonNull View itemView) {
        super(itemView);
        titleTV = itemView.findViewById(R.id.title);
        contentTV = itemView.findViewById(R.id.content);
    }
    public void bind(Notes notes) {
        titleTV.setText(notes.getTitle());
        contentTV.setText(notes.getContent());
    }
}
