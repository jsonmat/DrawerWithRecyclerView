package com.jaysontamayo.drawer.ui.notes;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jaysontamayo.drawer.R;
import com.jaysontamayo.drawer.data.CountryModel;
import com.jaysontamayo.drawer.data.NoteModel;

import java.util.List;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<NoteModel> localDataSet;
    private final OnItemClickListener localListener;
    private final OnDeleteClickListener localListenerFave;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(NoteModel item);
    }

    public interface OnDeleteClickListener {
        void onItemClick(NoteModel item, int position);
    }


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView description;
        private final CardView cardView;
        private final Button btnDelete;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.desc);
            cardView = view.findViewById(R.id.noteCardView);
            btnDelete = view.findViewById(R.id.btnDelete);
        }

        public TextView getTitle() {
            return title;
        }
        public TextView getDescription() {
            return description;
        }
        public CardView getCardView() {
            return cardView;
        }
        public Button getBtnDelete() {
            return btnDelete;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public NotesAdapter(List<NoteModel> dataSet, OnItemClickListener listener, OnDeleteClickListener listenerFave, Context context) {

        localDataSet = dataSet;
        localListener = listener;
        localListenerFave = listenerFave;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTitle().setText(localDataSet.get(position).noteTitle);
        viewHolder.getDescription().setText(localDataSet.get(position).noteDesc);
        viewHolder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localListener.onItemClick(localDataSet.get(position));
            }
        });
        viewHolder.getBtnDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localListenerFave.onItemClick(localDataSet.get(position), position);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

