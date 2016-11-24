package com.github.handioq.baseandroid.logic.network.retrofit2.request;

import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.request.SpiceRequest;

public abstract class Retrofit2SpiceRequest<T, R> extends SpiceRequest<T> {

    private Class<R> retrofitedInterfaceClass;
    private R service;
    private CacheManager cacheManager;

    public Retrofit2SpiceRequest(Class<T> clazz, Class<R> retrofitedInterfaceClass) {
        super(clazz);
        this.retrofitedInterfaceClass = retrofitedInterfaceClass;
    }

    public Class<R> getRetrofitedInterfaceClass() {
        return retrofitedInterfaceClass;
    }

    public void setService(R service) {
        this.service = service;
    }

    public R getService() {
        return service;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }
}
