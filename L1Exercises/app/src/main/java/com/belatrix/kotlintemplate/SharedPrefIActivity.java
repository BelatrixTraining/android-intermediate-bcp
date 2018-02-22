package com.belatrix.kotlintemplate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SharedPrefIActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button,button2,button3,button4;
    private Button button5,button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref_01);
        ui();
    }

    private void ui() {
        button= findViewById(R.id.button);
        button2= findViewById(R.id.button2);
        button3= findViewById(R.id.button3);
        button4= findViewById(R.id.button4);

        button5= findViewById(R.id.button5);
        button6= findViewById(R.id.button6);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        button5.setOnClickListener(this);
        button6.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                    selectedColor("#000000");
                break;
            case R.id.button5:
                save();
                break;
            case R.id.button6:
                reset();
                break;
        }
    }

    private void selectedColor(String color){

    }

    private void save(){

    }

    private void reset(){

    }
}
