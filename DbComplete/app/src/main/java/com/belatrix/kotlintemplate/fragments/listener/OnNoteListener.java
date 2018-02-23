package com.belatrix.kotlintemplate.fragments.listener;


import com.belatrix.kotlintemplate.model.NoteEntity;
import com.belatrix.kotlintemplate.storage.CRUDOperations;

/**
 * Created by emedinaa on 15/09/15.
 */
public interface OnNoteListener {

     CRUDOperations getCrudOperations();
     void deleteNote(NoteEntity noteEntity);
     void editNote(NoteEntity noteEntity);
}
