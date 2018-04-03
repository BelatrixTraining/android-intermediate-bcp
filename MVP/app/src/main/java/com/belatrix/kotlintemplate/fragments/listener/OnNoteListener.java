package com.belatrix.kotlintemplate.fragments.listener;


import com.belatrix.kotlintemplate.model.NoteBLEntity;

/**
 * Created by emedinaa on 15/09/15.
 */
public interface OnNoteListener {


     void editNoteNetwork(NoteBLEntity noteEntity);
     void deleteNoteNetwork(NoteBLEntity noteEntity);

     void showLoading();
     void hideLoading();
}
