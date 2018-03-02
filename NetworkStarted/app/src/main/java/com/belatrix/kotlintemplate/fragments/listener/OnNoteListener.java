package com.belatrix.kotlintemplate.fragments.listener;


import com.belatrix.kotlintemplate.model.NoteDbEntity;
import com.belatrix.kotlintemplate.storage.CRUDOperations;
import com.belatrix.kotlintemplate.storage.NoteRepository;

/**
 * Created by emedinaa on 15/09/15.
 */
public interface OnNoteListener {

     CRUDOperations getCrudOperations();
     NoteRepository getNoteRepository();
     void deleteNote(NoteDbEntity noteDbEntity);
     void editNote(NoteDbEntity noteDbEntity);
}
