package com.example.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

public class NotesPaginglistAdapter extends PagedListAdapter<Notes,listviewHolder> {
    private ClickListener clickListener;
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
    public void onBindViewHolder(@NonNull final listviewHolder holder, final int position) {
    Notes currentNotes = getItem(position);
    if (currentNotes!=null) {
        if (clickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.itemClick(position,v);
                }
            });
        }

    }
    }
    public Notes getNotesAtPosition(int position){
        return getItem(position);
    }

    public void onItemClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    public interface ClickListener{
        void itemClick(int position,View view);
    }
    private static DiffUtil.ItemCallback<Notes> DIFF_CALLBACK = new DiffUtil.ItemCallback<Notes>() {
        @Override
        public boolean areItemsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem.isNotesEqual(newItem);
        }
    };
}
