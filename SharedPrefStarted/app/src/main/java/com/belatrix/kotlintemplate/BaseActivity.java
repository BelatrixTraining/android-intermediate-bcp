package com.belatrix.kotlintemplate;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by emedinaa on 2/22/18.
 */

public class BaseActivity extends AppCompatActivity {

    protected void enabledBack(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
