package com.belatrix.kotlintemplate.storage.network.entity;

import com.belatrix.kotlintemplate.model.NoteEntity;

/**
 * Created by emedinaa on 14/10/17.
 */

public class NoteResponse extends BaseResponse {

    private NoteEntity data;

    public NoteEntity getData() {
        return data;
    }

    public void setData(NoteEntity data) {
        this.data = data;
    }
}
