package com.belatrix.kotlintemplate.presenter;

import com.belatrix.kotlintemplate.model.NoteBLEntity;

import java.util.List;

/**
 * Created by eduardomedina on 13/03/18.
 */

public interface NoteListView {

    void renderNotesBL(List<NoteBLEntity> mNotes);
    void  showErrorMessage(String error);
    void  showMessage(String message);

    void showLoading();
    void hideLoading();

    void goToNote(int action, NoteBLEntity noteEntity);

}
