package com.jaysontamayo.drawer.ui.sample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jaysontamayo.drawer.R;
import com.jaysontamayo.drawer.ui.gallery.CustomAdapter;

import java.util.List;

public class SampleCustomAdapter extends RecyclerView.Adapter<SampleCustomAdapter.ViewHolder> {

    private List<NameModel> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView title2;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            title = (TextView) view.findViewById(R.id.title);
            title2 = (TextView) view.findViewById(R.id.title2);
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getTitle2() {
            return title2;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public SampleCustomAdapter(List<NameModel> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.sample_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTitle().setText(localDataSet.get(position).lastName);
        viewHolder.getTitle2().setText(localDataSet.get(position).firstName);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
