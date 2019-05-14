package com.xishan.smartapicaller;

import retrofit2.Call;
import retrofit2.Response;

public interface ResponseHandler {

    void onSuccess(Call call, Response response, int reqCode);

    void onFailure(Call call, GenericResponse error, int reqCode);

    void onApiCrash(Call call, Throwable t, int reqCode);

}
