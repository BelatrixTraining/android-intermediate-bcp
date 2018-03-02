package com.belatrix.kotlintemplate.storage.network;

import com.belatrix.kotlintemplate.storage.network.entity.NotesResponse;
import com.google.gson.GsonBuilder;

/**
 * Created by eduardomedina on 1/03/18.
 */

public class GsonHelper {

    public static NotesResponse jsonToNotesResponse (String json){
        if(json==null){
            return new NotesResponse();
        }
        GsonBuilder gson = new GsonBuilder();
        //Type collectionType = new TypeToken<T>(){}.getType();
        NotesResponse notesResponse = gson.create().fromJson(json, NotesResponse.class);

        return notesResponse;
    }
}
