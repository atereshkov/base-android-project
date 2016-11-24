package com.github.handioq.baseandroid.logic.network;

import android.text.TextUtils;
import android.util.Log;

import com.github.handioq.baseandroid.model.dto.BaseResponse;
import com.github.handioq.baseandroid.utils.GsonHelper;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NetworkInterceptor implements Interceptor {

    public static final String TAG = NetworkInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder();
        Request request = requestBuilder.build();
        Response response = chain.proceed(request);
        if (response.isSuccessful()) {
            String originalResponse = response.body().string();
            String jsonData = originalResponse;
            try {
                Log.i(TAG, "okHttp body:" + jsonData);
                if (!TextUtils.isEmpty(jsonData.trim())) {
                    BaseResponse error = GsonHelper.getGson().fromJson(jsonData, BaseResponse.class);
                    if (error != null && !TextUtils.isEmpty(error.getErrorCode())) {
                        throw new IOException(error.toError());
                    }
                } else {
                    Log.e(TAG, "Empty response");
                    originalResponse = "{}";
                }
            } catch (JsonSyntaxException e) {
                Log.e(TAG, "Obtain from server. " + jsonData);
                Log.v(TAG, e.toString(), e);
            }
            MediaType contentType = response.body().contentType();
            ResponseBody body = ResponseBody.create(contentType, originalResponse);
            response = response.newBuilder().body(body).build();
        }
        return response;
    }
}
