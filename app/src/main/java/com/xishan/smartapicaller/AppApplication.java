package com.xishan.smartapicaller;

import android.app.Application;

public class AppApplication extends Application {


    public static ApiInterface smartCaller;

    @Override
    public void onCreate() {
        super.onCreate();
        SmartApiCaller.BASE_URL = "https://api.github.com/";
        smartCaller = SmartApiCaller.getRestClient(ApiInterface.class);
    }

    public void setHeader(String key, String token){
        RetroClient.setHeader(key, token);
    }
}
