package com.belatrix.kotlintemplate.presenter;

import com.belatrix.kotlintemplate.storage.network.ApiClient;
import com.belatrix.kotlintemplate.storage.network.StorageConstant;
import com.belatrix.kotlintemplate.storage.network.entity.NotesBLResponse;
import com.belatrix.kotlintemplate.storage.preferences.PreferencesHelper;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eduardomedina on 13/03/18.
 */

public class NoteListPresenter {

    private final PreferencesHelper preferencesHelper;
    private NoteListView view;

    public NoteListPresenter(PreferencesHelper preferencesHelper) {
        this.preferencesHelper = preferencesHelper;
    }

    public void loadDataBackendless(String token){
        view.showLoading();

        //String token= PreferencesHelper.getTokenSession(this);

        Map<String, String> map = new HashMap<>();
        map.put("user-token",token);
        Call<NotesBLResponse> call= ApiClient.getMyApiClient().notesbl(
                StorageConstant.APPLICATIONID, StorageConstant.RESTAPIKEY,map);

        call.enqueue(new Callback<NotesBLResponse>() {
            @Override
            public void onResponse(Call<NotesBLResponse> call, Response<NotesBLResponse> response) {
                view.hideLoading();
                if(response!=null){
                    NotesBLResponse notesResponse=null;
                    if(response.isSuccessful()){
                        notesResponse= response.body();
                        if(notesResponse.isEmpty()){
                            view.emptyNotes();
                        }else{
                            view.renderNotesBL(notesResponse);
                        }
                    }else{
                    }
                }
            }

            @Override
            public void onFailure(Call<NotesBLResponse> call, Throwable t) {
                view.hideLoading();
                view.showMessage(t.getMessage());
            }
        });
    }

    public void logout(){
        view.logOut();
    }
    public void attachView(NoteListView view){
        this.view=view;
    }
    public void detachView(){
        this.view=null;
    }
}
