package com.belatrix.kotlintemplate.presenter;

import android.util.Log;

import com.belatrix.kotlintemplate.storage.network.ApiClient;
import com.belatrix.kotlintemplate.storage.network.entity.LogInRaw;
import com.belatrix.kotlintemplate.storage.network.entity.LogInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eduardomedina on 13/03/18.
 */

public class LogInCPresenter implements LogInContract.Presenter {

    private LogInContract.View view;

    @Override
    public void logIn() {
        if(view.validateForm()){
            authentication();
        }
    }

    @Override
    public void authentication() {
        String username= view.getUsername();
        String password= view.getPassword();

        final LogInRaw logInRaw= new LogInRaw();
        logInRaw.setUsername(username);
        logInRaw.setPassword(password);

        Call<LogInResponse> call= ApiClient.getMyApiClient().login(logInRaw);
        call.enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                view.hideLoading();
                if(response!=null){
                    LogInResponse logInResponse=null;

                    if(response.isSuccessful()){
                        logInResponse=response.body();
                        if(logInResponse!=null){
                            if(logInResponse.getStatus()==200){
                                Log.v("CONSOLE", "success "+logInResponse);
                                //saveSession(logInResponse);
                                view.gotoMain();
                            }else{
                                Log.v("CONSOLE", "error "+logInResponse);
                            }
                        }
                    }else{
                        Log.v("CONSOLE", "error "+logInResponse);

                        /*JSONObject jsonObject = null;
                        try {
                            jsonObject=new JSONObject (response.errorBody().string());
                        }catch (Exception e){
                            jsonObject= new JSONObject();
                        }

                        logInResponse= GsonHelper.responseToObject(jsonObject.toString());
                        showMessage(logInResponse.getMsg());*/
                    }
                }else{
                    view.showMessage("Ocurrió un error");
                }
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                view.hideLoading();
                view.showMessage(t.getMessage());
                /*Toast.makeText(LoginActivity.this,
                        "error "+t.getMessage(),Toast.LENGTH_LONG).show();*/
                //hideLoading();
            }
        });
    }

    @Override
    public void goToUserRegister() {
        this.view.gotoUserRegister();
    }

    @Override
    public void cancelRequest() {

    }

    @Override
    public void attachView(LogInContract.View view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        this.view=null;
    }
}
