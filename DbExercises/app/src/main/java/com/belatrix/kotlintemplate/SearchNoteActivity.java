package com.belatrix.kotlintemplate;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import com.belatrix.kotlintemplate.storage.NoteRepository;

public class SearchNoteActivity extends BaseActivity {

    private EditText editextSearch;
    private ListView listViewNotes;
    private NoteRepository noteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_note);
        enabledBack();
        noteRepository= ((NoteApplication)(getApplication())).getNoteRepository();
        ui();
    }

    private void ui() {
        editextSearch= findViewById(R.id.editextSearch);
        listViewNotes= findViewById(R.id.listViewNotes);

        editextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.v("CONSOLE", "beforeTextChanged "+s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.v("CONSOLE", "onTextChanged "+s.toString());
                if(s!=null && s.length()>3){
                    search(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.v("CONSOLE", "afterTextChanged "+s.toString());
            }
        });
    }

    private void search(String query) {
        //noteRepository.cancelTask();
        //noteRepository.getAllNotes();
    }
}
