package com.udacity.jwdnd.course1.cloudstorage.model;

public class NoteForm {
    private String notetitle;
    private String notedescription;
    private String noteid;

    public NoteForm() {
    }

    public String getNoteid() {
        return noteid;
    }

    public void setNoteId(String noteId) {
        this.noteid = noteId;
    }



    public String getNotetitle() {
        return notetitle;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }

    public String getNotedescription() {
        return notedescription;
    }

    public void setNotedescription(String notedescription) {
        this.notedescription = notedescription;
    }
}