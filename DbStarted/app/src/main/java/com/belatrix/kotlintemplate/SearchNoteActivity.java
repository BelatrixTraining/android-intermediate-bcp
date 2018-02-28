package com.belatrix.kotlintemplate;

import android.os.Bundle;

public class SearchNoteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_note);
        enabledBack();
    }
}
