package com.jaysontamayo.drawer.ui.gallery;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jaysontamayo.drawer.R;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private String[] localDataSet;
    private int[] localDataSetImages;
    private String[] localDataSetDesc;
    private final OnItemClickListener localListener;

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView description;
        private final ImageView header_image;
        private final CardView cardView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            header_image = view.findViewById(R.id.header_image);
            cardView = view.findViewById(R.id.cardView);
        }

        public TextView getTitle() {
            return title;
        }
        public TextView getDescription() {
            return description;
        }
        public ImageView getHeaderImage() {
            return header_image;
        }
        public CardView getCardView() {
            return cardView;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public CustomAdapter(String[] dataSet, int[] dataSetImages, String[] dataSetDesc, OnItemClickListener listener) {

        localDataSet = dataSet;
        localDataSetImages = dataSetImages;
        localDataSetDesc = dataSetDesc;
        localListener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Log.i("", "" + localDataSet[position]);
        viewHolder.getTitle().setText(localDataSet[position]);
        viewHolder.getHeaderImage().setImageResource(localDataSetImages[position]);
        viewHolder.getDescription().setText(localDataSetDesc[position]);
        viewHolder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localListener.onItemClick(localDataSet[position]);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}

