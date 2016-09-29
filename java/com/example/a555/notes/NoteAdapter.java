package com.example.a555.notes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a555.notes.models.NoteDB;

/**
 * Created by 555 on 25.09.2016.
 */

public class NoteAdapter extends CursorAdapter {

    public NoteAdapter(Context context, Cursor cursor){
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        TextView text = (TextView) view.findViewById(R.id.item_note_text);
        TextView date = (TextView) view.findViewById(R.id.item_note_date);

        int id = cursor.getInt(cursor.getColumnIndex(NoteDB.ID));
        view.setTag(id);

        int keyText = cursor.getColumnIndex(NoteDB.COL_TEXT);
        int keyDate = cursor.getColumnIndex (NoteDB.COL_DATE);

        text.setText(cursor.getString(keyText));
        date.setText(cursor.getString(keyDate));

    }


}
