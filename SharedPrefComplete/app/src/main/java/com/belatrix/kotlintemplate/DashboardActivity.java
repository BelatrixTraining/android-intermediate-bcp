package com.belatrix.kotlintemplate;

import android.os.Bundle;

public class DashboardActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        enabledBack();
    }
}
