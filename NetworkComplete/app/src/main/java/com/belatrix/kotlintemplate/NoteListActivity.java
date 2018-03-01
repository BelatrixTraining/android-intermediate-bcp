package com.belatrix.kotlintemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.belatrix.kotlintemplate.model.NoteDbEntity;

import com.belatrix.kotlintemplate.model.NoteEntity;
import com.belatrix.kotlintemplate.storage.NoteRepository;
import com.belatrix.kotlintemplate.storage.network.ApiClient;
import com.belatrix.kotlintemplate.storage.network.GsonHelper;
import com.belatrix.kotlintemplate.storage.network.entity.NotesResponse;
import com.belatrix.kotlintemplate.ui.adapters.NoteAdapter;
import com.belatrix.kotlintemplate.ui.adapters.NoteDbAdapter;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoteListActivity extends AppCompatActivity {

    private final String TAG="NoteList";
    private static final int ACTION_ADD=1;
    private static final int ACTION_DETAIL=2;

    private TextView tviLogout,tviUser;
    private ListView lstNotes;
    private Button btnAddNote;
    private List<NoteDbEntity> lsNoteEntities;
    private List<NoteEntity> notes;
    private NoteDbAdapter noteDbAdapter;
    private NoteAdapter noteAdapter;
    private NoteRepository noteRepository;
    private View flayLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        init();

        loadDataNetwork();
    }

    private void loadDataRoom() {
        noteRepository= ((NoteApplication)(getApplication())).getNoteRepository();

        noteRepository.getAllNotes(new NoteRepository.PopulateCallback() {
            @Override
            public void onSuccess(List<NoteDbEntity> noteEntities) {
                renderDBNotes(noteEntities);
            }

            @Override
            public void onFailure(Exception e) {
                showErrorMessage(e.toString());
            }
        });
    }

    private void loadDataNetwork(){
        showLoading();
        Call<NotesResponse> call= ApiClient.getMyApiClient().notes();
        call.enqueue(new Callback<NotesResponse>() {
            @Override
            public void onResponse(Call<NotesResponse> call, Response<NotesResponse> response) {
                hideLoading();

                if(response!=null){
                    NotesResponse notesResponse=null;

                    if(response.isSuccessful()){
                        notesResponse=response.body();
                        if(notesResponse!=null){
                            if(notesResponse.getStatus()==200){
                                Log.v("CONSOLE", "success "+notesResponse);
                                renderNotes(notesResponse.getData());
                            }else{
                                Log.v("CONSOLE", "error "+notesResponse);
                            }
                        }
                    }else{
                        Log.v("CONSOLE", "error "+notesResponse);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject=new JSONObject(response.errorBody().string());
                        }catch (Exception e){
                            jsonObject= new JSONObject();
                        }

                        notesResponse= GsonHelper.jsonToNotesResponse(jsonObject.toString());
                        showErrorMessage(notesResponse.getMsg());
                    }
                }else{
                    showErrorMessage("Ocurrió un error");
                }
            }

            @Override
            public void onFailure(Call<NotesResponse> call, Throwable t) {
                hideLoading();
                Toast.makeText(NoteListActivity.this,
                        "error "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
    private void renderDBNotes(List<NoteDbEntity> noteEntities){
        lsNoteEntities= noteEntities;
        noteDbAdapter = new NoteDbAdapter(this,lsNoteEntities);
        lstNotes.setAdapter(noteDbAdapter);
    }

    private void renderNotes(List<NoteEntity> noteEntities){
        notes= noteEntities;
        noteAdapter = new NoteAdapter(this,notes);
        lstNotes.setAdapter(noteAdapter);
    }

    private void showErrorMessage(String error){
        Toast.makeText(this,"Error : "+error,Toast.LENGTH_SHORT).show();
    }

    /* Error
    Caused by: java.lang.IllegalStateException: Cannot access database on the main thread since it may potentially lock the UI for a long period of time.
     */
    private void populateRoom() {
        noteRepository= ((NoteApplication)(getApplication())).getNoteRepository();

        //new NoteDbEntity("Mi Nota","Esta es un nota ",null)
        noteRepository.addNotes(new NoteDbEntity(1,"Mi Nota","Esta es un nota ",null),
        new NoteDbEntity(2,"Segunda Nota","Esta es la segunds nota ",null),
        new NoteDbEntity(3,"Tercera Nota","Esta es la tercera nota ",null),
        new NoteDbEntity(4,"Cuarta Nota","Esta es la cuarta nota ",null),
        new NoteDbEntity(5,"Quinta Nota","Esta es la quinta nota ",null),
        new NoteDbEntity(6,"Sexta Nota","Esta es la sexta nota ",null));

        //Log.v(TAG, "populate " + crudOperations.getAllNotes());
    }

    private void init() {
        tviLogout= (TextView)findViewById(R.id.tviLogout);
        tviUser= (TextView)findViewById(R.id.tviUser);
        lstNotes= (ListView)(findViewById(R.id.lstNotes));
        btnAddNote= (Button)(findViewById(R.id.btnAddNote));
        flayLoading= (findViewById(R.id.flayLoading));

        //events
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoNote(ACTION_ADD, null);
            }
        });

        lstNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NoteDbEntity noteDbEntity = (NoteDbEntity) adapterView.getAdapter().getItem(i);
                gotoNote(ACTION_DETAIL, noteDbEntity);
            }
        });

        tviLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void showLoading(){
        flayLoading.setVisibility(View.VISIBLE);
    }

    private void hideLoading(){
        flayLoading.setVisibility(View.GONE);
    }

    private void logout() {
    }

    private void gotoNote(int action, NoteDbEntity noteDbEntity) {
        Intent intent= new Intent(this,NoteActivity.class);

        switch (action)
        {
            case ACTION_ADD:
                intent.putExtra("FRAGMENT",NoteActivity.ADD_NOTE);
                startActivity(intent);
                break;
            case ACTION_DETAIL:
                /*intent.putExtra("FRAGMENT",NoteActivity.DETAIL_NOTE);
                intent.putExtra("NOTE", noteDbEntity);
                startActivity(intent);*/
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        //loadDataRoom();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
