package com.jaysontamayo.drawer.data;

public class NoteModel {
    public int noteID;
    public String noteTitle;
    public String noteDesc;

    NoteModel(int noteID, String noteTitle, String noteDesc){
        this.noteID = noteID;
        this.noteTitle = noteTitle;
        this.noteDesc = noteDesc;
    }

    @Override
    public String toString() {
        return "NoteModel{" +
                "noteID=" + noteID +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteDesc='" + noteDesc + '\'' +
                '}';
    }
}
