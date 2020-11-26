package com.example.automatedhomehydroponics.ui.Notes;

import org.bson.types.ObjectId;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Notes extends RealmObject {
    @PrimaryKey
    private ObjectId _id;
    @Required
    private String Notes;
    @Required
    private String title;

    // Standard getters & setters
    public ObjectId get_id() { return _id; }
    public void set_id(ObjectId _id) { this._id = _id; }
    public String getNotes() { return Notes; }
    public void setNotes(String Notes) { this.Notes = Notes; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
