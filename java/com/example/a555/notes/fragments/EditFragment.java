package com.example.a555.notes.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a555.notes.R;
import com.example.a555.notes.models.Note;
import com.example.a555.notes.models.NoteDB;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 555 on 25.09.2016.
 */

public class EditFragment extends Fragment {
    public static final int OPEN = 3;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.edit_fragment, container, false);
        final EditText editText = (EditText)view.findViewById(R.id.edit_fragment_textedit);
        Button saveBtn = (Button)view.findViewById(R.id.edit_fragment_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                SimpleDateFormat format = new SimpleDateFormat("d.MM.yyyy");
                String date = format.format(new Date());
                NoteDB.getInstance(inflater.getContext()).addNote(new Note(text,date));
                Toast.makeText(inflater.getContext(), "Note save", Toast.LENGTH_SHORT).show();

                // hide keyboard
                InputMethodManager inputManager = (InputMethodManager) inflater.getContext().
                                getSystemService(inflater.getContext().INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow( editText.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
            }
        });
        return view;
    }
}
