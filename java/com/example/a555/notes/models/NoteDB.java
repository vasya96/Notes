package com.example.a555.notes.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by 555 on 25.09.2016.
 */

public class NoteDB extends SQLiteOpenHelper {

    private static NoteDB instance;
    SQLiteDatabase db;


    final static String DB_NAME = "NOTE_DATABASE";
    public final static String TABLE_NAME = "NOTES";
    public final static String ID = "_id";
    public final static String COL_TEXT = "mtext";
    public final static String COL_DATE = "mdate";

    public NoteDB(Context context){
        super(context,DB_NAME, null, 1);
    }

    public static synchronized NoteDB getInstance(Context context){
        if(instance == null){
            instance = new NoteDB(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String init = "CREATE TABLE " +
                TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TEXT+" TEXT, " +
                COL_DATE+" TEXT);";
         db.execSQL(init);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addNote(Note note){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_TEXT, note.getText());
        cv.put(COL_DATE, note.getDate());

        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    public Cursor getNotes(){
        db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        //db.close();
        return c;
    }
    public Note getNote(int id){
        db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, null, ID+" = ?", new String[]{String.valueOf(id)}, null,null,null);

        String text;
        String date;
        c.moveToFirst();
        // reusing argument id variable
        id = c.getInt(c.getColumnIndex(ID));
        text = c.getString(c.getColumnIndex(COL_TEXT));
        date = c.getString(c.getColumnIndex(COL_DATE));

        return new Note(id , text, date);
    }

    public void removeNote(Note n){
        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_TEXT +"= ? and "+COL_DATE+" = ?", new String[]{n.text, n.date});
    }
    public void remoteNote(int id){
        db =  this.getWritableDatabase();
        db.delete(TABLE_NAME, ID +" = ?", new String[]{String.valueOf(id)});
    }
}


