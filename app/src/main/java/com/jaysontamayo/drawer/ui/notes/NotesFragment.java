package com.jaysontamayo.drawer.ui.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.service.controls.actions.FloatAction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jaysontamayo.drawer.R;
import com.jaysontamayo.drawer.data.CountryModel;
import com.jaysontamayo.drawer.data.DatabaseHelper;
import com.jaysontamayo.drawer.data.NoteModel;
import com.jaysontamayo.drawer.ui.gallery.CustomAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    NotesAdapter notesAdapter = null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<NoteModel> data; //nilabas natin to para ma-access ng inner methods

    public NotesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotesFragment newInstance(String param1, String param2) {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notes, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.rvNotes);
        DatabaseHelper db = new DatabaseHelper(root.getContext());
        data = db.getAllNotes();
        notesAdapter = new NotesAdapter(
                data,
                new NotesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(NoteModel item) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("noteID", item.noteID);
                        Navigation.findNavController(root).navigate(R.id.nav_edit_note, bundle);
                    }
                },
                new NotesAdapter.OnDeleteClickListener() {
                    @Override
                    public void onItemClick(NoteModel item, int position) {
                        db.deleteNote(item.noteID);
                        data.remove(position);
                        notesAdapter.notifyDataSetChanged();
                        Snackbar.make(root, item.noteTitle + " has been deleted.", Snackbar.LENGTH_LONG).show();
                    }
                },
                root.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(notesAdapter);

        FloatingActionButton fabAddNote = root.findViewById(R.id.fabAddNote);
        fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.nav_add_note);
            }
        });

        //SEARCH
        EditText searchBar = root.findViewById(R.id.searchBar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //WAG DITO
            }

            @Override
            public void afterTextChanged(Editable s) {
                //WAG RIN DTO
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //PAG NAGBAGO UNG TEXT SA SEARCH BAR, ITO UNG MA-TRIGGER
                //CHECK MUNA NATIN KUNG WALANG LAMAN UNG STRING NA NASA SEARCH BAR
                if(s.length() == 0){
                    //PAG WALANG LAMAN, KUNIN NATIN LAHAT NG RECORDS
                    data.clear(); //clear muna ntn ang laman kasi pag hindi na-clear, dadagdag lang ung nasa baba
                    data.addAll(db.getAllNotes()); //i-add natin ung naretrieve ntn mula sa database
                    notesAdapter.notifyDataSetChanged(); //notify natin ung adapter natin na nagbago ung data na gingamit nya para magreload sya
                }else{
                    //PAG MAY LAMAN, ITO ANG GAGAWIN NATIN
                    //KUNIN KO UNG TEXT SA SEARCH BAR
                    String input = s.toString();

                    data.clear(); //clear muna ntn ang laman kasi pag hindi na-clear, dadagdag lang ung nasa baba
                    data.addAll(db.getNotesWithMatches(input)); //TAWAGIN KO UNG METHOD NG DATABASEHELPER NA PANG RETRIEVE NG RECORDS NA MAY WHERE CLAUSE. I-ADD SYA SA DATA NA LIST
                    notesAdapter.notifyDataSetChanged(); //notify natin ung adapter natin na nagbago ung data na gingamit nya para magreload sya

                }
            }


        });


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}