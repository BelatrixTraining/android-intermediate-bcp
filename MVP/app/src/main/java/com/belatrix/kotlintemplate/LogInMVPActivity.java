package com.belatrix.kotlintemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.belatrix.kotlintemplate.presenter.LogInPresenter;
import com.belatrix.kotlintemplate.presenter.LogInView;
import com.belatrix.kotlintemplate.storage.network.entity.LogInBLResponse;
import com.belatrix.kotlintemplate.storage.preferences.PreferencesHelper;

/**
 * Created by eduardomedina on 13/03/18.
 */

public class LogInMVPActivity extends AppCompatActivity implements LogInView {

    private Button btnLogin,btnRegister;
    private EditText eteUsername;
    private EditText etePassword;
    private String username;
    private String password;

    private View flayLoading;
    private LogInPresenter logInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpPresenter();
        init();
    }


    private void setUpPresenter(){
        logInPresenter= new LogInPresenter();
        logInPresenter.attachView(this);
    }

    private void init() {
        //ui
        eteUsername=(EditText)findViewById(R.id.eteUsername);
        etePassword=(EditText)findViewById(R.id.etePassword);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        flayLoading=findViewById(R.id.flayLoading);

        //events
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logInPresenter.logIn();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUserRegister();
                //logInPresenter.goToUserRegister();
            }
        });

        etePassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if(validateForm()){
                        logInPresenter.logIn();
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void showLoading() {
        flayLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        flayLoading.setVisibility(View.GONE);
    }

    @Override
    public void gotoMain() {
        //Intent intent= new Intent(this,NoteListActivity.class);
        Intent intent= new Intent(this,NoteListMVPActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoUserRegister() {
        //Intent intent= new Intent(this,RegisterActivity.class);
        //startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,
                "LogIn "+message,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean validateForm() {
        username= eteUsername.getText().toString();
        password= etePassword.getText().toString();

        if(username.isEmpty())
        {
            eteUsername.setError("Error campo username");
            return false;
        }
        if(password.isEmpty())
        {
            etePassword.setError("Error campo password");
            return false;
        }
        return true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void saveSession(LogInBLResponse logInResponse) {

        PreferencesHelper.saveBLSession(this,logInResponse.getEmail(),logInResponse.getToken());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(logInPresenter!=null){
            logInPresenter.detachView();
        }
    }
}
