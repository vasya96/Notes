package com.example.a555.notes.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.a555.notes.R;
import com.example.a555.notes.models.Note;

/**
 * Created by 555 on 29.09.2016.
 */

public class DetailFragment extends Fragment {
    public static final int OPEN = 2;
    private Note note ;

    public DetailFragment(){}

    public static DetailFragment newInstance(Note note){
        DetailFragment fragment = new DetailFragment();
        fragment.note = note;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        TextView textView =(TextView) view.findViewById(R.id.detail_fragment_textview);
        ImageButton button = (ImageButton) view.findViewById(R.id.detail_fragment_btnEdit);

        if(note != null){
            textView.setText(note.getText());
        }

        return view;
    }
}
