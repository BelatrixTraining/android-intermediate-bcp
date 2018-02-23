package com.belatrix.kotlintemplate;

import android.os.Bundle;
import android.widget.TextView;

import com.belatrix.kotlintemplate.storage.PreferencesHelper;

public class DashboardActivity extends BaseActivity {

    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        enabledBack();

        textView2= findViewById(R.id.textView2);
        showUserName();
    }

    private void showUserName() {
        String username= PreferencesHelper.getUserSession(this);
        textView2.setText(username);
    }
}
