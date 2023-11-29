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
import com.jaysontamayo.drawer.R;
import com.jaysontamayo.drawer.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        CustomAdapter customAdapter = new CustomAdapter(new String[]{"Ireland", "Iceland", "Greenland", "Poland", "Finland", "Netherlands"},
                new int[]{R.drawable.scenery1, R.drawable.scenery2, R.drawable.scenery1, R.drawable.scenery1, R.drawable.scenery1, R.drawable.scenery1},
                new String[]{getString(R.string.greenland_desc), getString(R.string.greenland_desc), getString(R.string.greenland_desc), getString(R.string.greenland_desc), getString(R.string.greenland_desc), getString(R.string.greenland_desc)},
                new CustomAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String item) {
                        Snackbar.make(root, item, Snackbar.LENGTH_LONG).show();
                    }
                });
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