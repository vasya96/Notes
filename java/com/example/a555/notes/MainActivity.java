package com.example.a555.notes;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.a555.notes.fragments.DetailFragment;
import com.example.a555.notes.fragments.EditFragment;
import com.example.a555.notes.fragments.MainFragment;
import com.example.a555.notes.models.Note;

public class MainActivity extends FragmentActivity implements MainFragment.OnFragmentChange {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new MainFragment())
                    .commit();
        }
    }

    @Override
    public void openFragment(int fragToOpen, Note note) {
        switch (fragToOpen){
            case EditFragment.OPEN:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new EditFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case DetailFragment.OPEN:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, DetailFragment.newInstance(note))
                        .addToBackStack(null)
                        .commit();
                break;

            case MainFragment.OPEN:
                break;
        }
    }
}
