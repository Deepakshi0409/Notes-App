package com.example.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

public class NotesPaginglistAdapter extends PagedListAdapter<Notes,listviewHolder> {
    protected NotesPaginglistAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public listviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item = layoutInflater.inflate(R.layout.list_item,parent,false);
        return new listviewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull listviewHolder holder, int position) {
    Notes currentNotes = getItem(position);
    if (currentNotes!=null) {
        holder.bind(currentNotes);
    }
    }
    private static DiffUtil.ItemCallback<Notes> DIFF_CALLBACK = new DiffUtil.ItemCallback<Notes>() {
        @Override
        public boolean areItemsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem.getTitle()== newItem.getTitle();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem.isNotesEqual(newItem);
        }
    };
}
