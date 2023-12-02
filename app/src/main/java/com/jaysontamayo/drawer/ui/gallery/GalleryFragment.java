package com.jaysontamayo.drawer.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.jaysontamayo.drawer.MainActivity;
import com.jaysontamayo.drawer.R;
import com.jaysontamayo.drawer.data.CountryModel;
import com.jaysontamayo.drawer.data.DatabaseHelper;
import com.jaysontamayo.drawer.databinding.FragmentGalleryBinding;

import java.util.List;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private CustomAdapter customAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        DatabaseHelper db = new DatabaseHelper(root.getContext());
        List<CountryModel> data = db.getAllCountries();
        customAdapter = new CustomAdapter(
                data,
                new CustomAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(CountryModel item) {
                        Snackbar.make(root, item.country, Snackbar.LENGTH_LONG).show();
                    }
                },
                new CustomAdapter.OnFaveClickListener() {
                    @Override
                    public void onItemClick(CountryModel item, int position) {
                        db.updateFavorite(item.countryID, item.favorite == 0 ? 1 : 0);
                        data.set(position, new CountryModel(item.countryID, item.country,item.description, item.capital, item.population, item.image, item.favorite == 0 ? 1 : 0));
                        customAdapter.notifyDataSetChanged();
                        Snackbar.make(root, item.country + " has been " + (item.favorite == 0 ? "added" : "removed") + " to your Favorites.", Snackbar.LENGTH_LONG).show();
                    }
                },
                root.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(customAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}