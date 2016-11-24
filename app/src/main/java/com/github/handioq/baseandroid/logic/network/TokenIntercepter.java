package com.github.handioq.baseandroid.logic.network;

import android.text.TextUtils;

import com.github.handioq.baseandroid.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenIntercepter implements Interceptor {
    private static final String AUTHORIZATION = "Authorization";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        // Nothing to add to intercepted request if:
        // a) Authorization value is empty because user is not logged in yet
        // b) There is already a header with updated Authorization value
        if (TextUtils.isEmpty(getToken())) {
            return chain.proceed(originalRequest);
        }

        // Add authorization header with updated authorization value to intercepted request
        Request authorisedRequest = originalRequest.newBuilder()
                .header(AUTHORIZATION, "Bearer " + getToken())
                .build();
        return chain.proceed(authorisedRequest);
    }

    private String getToken() {
        return Constants.TOKEN;
    }
}
