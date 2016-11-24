package com.github.handioq.baseandroid.logic.request;

import com.github.handioq.baseandroid.logic.Api;
import com.github.handioq.baseandroid.logic.network.request.BaseRequest;

/**
 * Common for all requests, provide api type only
 */
public abstract class CommonRequest<TYPE> extends BaseRequest<TYPE, Api> {
    public CommonRequest(Class<TYPE> responseClazz) {
        super(responseClazz, Api.class);
    }
}
