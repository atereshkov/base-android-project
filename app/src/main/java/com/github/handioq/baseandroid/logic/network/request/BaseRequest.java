package com.github.handioq.baseandroid.logic.network.request;

import android.util.Log;

import com.github.handioq.baseandroid.logic.network.retrofit2.request.Retrofit2SpiceRequest;

import retrofit2.Call;
import retrofit2.Response;

public abstract class BaseRequest<RESULT, INTERFACE> extends Retrofit2SpiceRequest<RESULT, INTERFACE> {
    public static final String TAG = BaseRequest.class.getSimpleName();

    public BaseRequest(Class<RESULT> clazz, Class<INTERFACE> retrofitedInterfaceClass) {
        super(clazz, retrofitedInterfaceClass);
    }

    @Override
    public RESULT loadDataFromNetwork() throws Exception {
        Log.d(TAG, "Try request:" + getClass().toString() + ", result: " + getResultType() + ", interface: " + getRetrofitedInterfaceClass());
        return load();
    }

    protected RESULT load() throws Exception {
        Response<RESULT> response = getCall().execute();
        //XXX add response handler
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new Exception(response.message());
        }
    }

    protected Call<RESULT> getCall() {
        return prepare(getService());
    }

    protected abstract Call<RESULT> prepare(INTERFACE api);
}
