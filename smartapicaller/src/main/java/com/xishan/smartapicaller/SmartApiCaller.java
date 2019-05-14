package com.xishan.smartapicaller;

import android.app.Application;

import retrofit2.Retrofit;

public class SmartApiCaller extends Application {

    public static String BASE_URL = "";
    private static SmartApiCaller _instance;


    public static SmartApiCaller getAppContext() {
        return _instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
    }

    public static <T> T getRestClient(Class<T> restApi) {
        return RetroClient.getRetroClient(restApi).getApiServices();
    }


    public static Retrofit getRetrofit(Class restApi) {
        return RetroClient.getRetroClient(restApi).getRetrofit();
    }

//     new SmartRestCaller(Login.this, SmartWashr.getRestClient().login(email.getText().toString(), password.getText().toString(), sessionManager.get(Constants.DEVICE_TOKEN)), 1);

}
