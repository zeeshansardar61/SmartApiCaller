package com.xishan.smartapicaller;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ResponseHandler {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        new SmartRestCaller(context,this, AppApplication.smartCaller.getRepo("trending", "stars"), 1);
    }

    @Override
    public void onSuccess(Call call, Response response, int reqCode) {

    }

    @Override
    public void onFailure(Call call, GenericResponse error, int reqCode) {

    }

    @Override
    public void onApiCrash(Call call, Throwable t, int reqCode) {

    }
}
