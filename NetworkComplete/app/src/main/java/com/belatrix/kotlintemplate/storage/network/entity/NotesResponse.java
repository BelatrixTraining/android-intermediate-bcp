package com.belatrix.kotlintemplate.storage.network.entity;
import com.belatrix.kotlintemplate.model.NoteEntity;

import java.util.List;

/**
 * Created by emedinaa on 14/10/17.
 */

public class NotesResponse {

    private String msg;

    private int status;

    private List<NoteEntity> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<NoteEntity> getData() {
        return data;
    }

    public void setData(List<NoteEntity> data) {
        this.data = data;
    }
}
