package com.belatrix.kotlintemplate.fragments.listener;


import com.belatrix.kotlintemplate.model.NoteDbEntity;
import com.belatrix.kotlintemplate.model.NoteEntity;
import com.belatrix.kotlintemplate.storage.NoteRepository;

/**
 * Created by emedinaa on 15/09/15.
 */
public interface OnNoteListener {

     NoteRepository getNoteRepository();
     void deleteNote(NoteDbEntity noteDbEntity);
     void editNote(NoteDbEntity noteDbEntity);

     void editNoteNetwork(NoteEntity noteEntity);
     void deleteNoteNetwork(NoteEntity noteEntity);

     void showLoading();
     void hideLoading();
}
