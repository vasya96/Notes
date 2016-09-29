package com.example.a555.notes.models;

import java.util.Date;

/**
 * Created by 555 on 25.09.2016.
 */

public class Note {
    int id;
    String text;
    String date;

    public Note(String text, String date) {
        this.text = text;
        this.date = date;
    }

    public Note(int id, String text, String date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
