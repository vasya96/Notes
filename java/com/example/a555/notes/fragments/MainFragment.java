package com.example.a555.notes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a555.notes.NoteAdapter;
import com.example.a555.notes.R;
import com.example.a555.notes.models.Note;
import com.example.a555.notes.models.NoteDB;


/**
 * Created by 555 on 25.09.2016.
 */

public class MainFragment extends Fragment {

    public static final int OPEN = 1;


    Context context ;
    NoteDB noteDB ;
    OnFragmentChange onFragmentChange;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        noteDB = NoteDB.getInstance(context);
        onFragmentChange = (OnFragmentChange) context;
    }



    public MainFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);


        final ListView listView = (ListView) view.findViewById(R.id.main_fragment_listview);

        final NoteAdapter adapter = new NoteAdapter(context, noteDB.getNotes());
        listView.setAdapter(adapter);

        //open DetailFragment
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = NoteDB.getInstance(context).getNote((int)view.getTag());
                onFragmentChange.openFragment(DetailFragment.OPEN, note);

            }
        });

        //setting pop up menu
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                PopupMenu itemMenu = new PopupMenu(context, view);
                itemMenu.getMenuInflater().inflate(R.menu.item_menu, itemMenu.getMenu());
                itemMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.item_note_delete:
                                NoteDB.getInstance(context).remoteNote((int)view.getTag());
                                adapter.swapCursor(NoteDB.getInstance(context).getNotes());
                                break;
                        }
                        return false;
                    }
                });
                itemMenu.setGravity(Gravity.RIGHT);
                itemMenu.show();
                return false;
            }
        });

        ImageButton addNoteBtn = (ImageButton) view.findViewById(R.id.main_fragment_btnadd);

        //go to EditFragment to add new note
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFragmentChange.openFragment(EditFragment.OPEN, null);
            }
        });



        return view;
    }

    public interface OnFragmentChange{
        public void openFragment(int fragment, Note note);
    }
}
