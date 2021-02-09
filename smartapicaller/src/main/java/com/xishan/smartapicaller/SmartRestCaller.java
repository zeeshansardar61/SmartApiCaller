package com.xishan.smartapicaller;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class SmartRestCaller {

    ResponseHandler handler;
    private int REQ_CODE = 0;
    Context context;

    public SmartRestCaller(Context context_, ResponseHandler responseHandler, Call caller, final int REQUEST_CODE) throws NumberFormatException {
        if (REQUEST_CODE <= 0) {
            NumberFormatException ex = new NumberFormatException();
            throw ex;
        }
        REQ_CODE = REQUEST_CODE;
        handler = responseHandler;
        context= context_;
        if (Internet.isAvailable(context_)) {
            ENQUE(caller);
        } else {
            Toast.makeText(context_, "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }
    }


    private void ENQUE(Call call) {
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.code() == 200 | response.code() == 201) {
                    handler.onSuccess(call, response, REQ_CODE);
                } else if (response.code() == 403) {
                    Toast.makeText(context, "Code: 403", Toast.LENGTH_SHORT).show();

                } else {
                    GenericResponse error = null;
                    Converter<ResponseBody, GenericResponse> errorConverter =
                            SmartApiCaller.getRetrofit(RetroClient.mRestService).responseBodyConverter(GenericResponse.class, new Annotation[0]);
                    try {
                        error = errorConverter.convert(response.errorBody());
                    } catch (IOException e) {
                    }
                    if (error != null)
                        handler.onFailure(call, error, REQ_CODE);
                    else
                        handler.onApiCrash(call, new Throwable(response.raw().message()), REQ_CODE);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (t instanceof UnknownHostException)
                    handler.onApiCrash(call, new Throwable("Unable to access server. Please check your connection."), REQ_CODE);
                else
                    handler.onApiCrash(call, t, REQ_CODE);
            }
        });
    }

}