package com.xishan.smartapicaller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    public static Class<?> mRestService;
    private Retrofit retrofit = null;
    private static RetroClient object;
    private Object service;
    public static OkHttpClient.Builder httpClient;

    private <T> RetroClient(Class<T> restService) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.connectTimeout(60, TimeUnit.SECONDS);

        httpClient.addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY));
        retrofit = new Retrofit.Builder()
                .baseUrl(SmartApiCaller.BASE_URL)
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        mRestService = restService;
        service = retrofit.create(restService);

    }

    public static <T> RetroClient getRetroClient(Class<T> restApi) {
        if (object == null) {
            object = new RetroClient(restApi);
        } else if (object != null && !object.getRetrofit().baseUrl().toString().equalsIgnoreCase(SmartApiCaller.BASE_URL)) {
            object = new RetroClient(restApi);
        }
        return object;
    }

    public static void setHeader(final String key, final String token){
        final Interceptor addHeaderInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader(key, token)
                        .build();
                return chain.proceed(newRequest);
            }
        };
        httpClient.addInterceptor(addHeaderInterceptor);
    }


    <T> T getApiServices() {
        return (T) object.service;
    }

    Retrofit getRetrofit() {
        return object.retrofit;
    }
}
