package com.github.handioq.baseandroid.logic.network.retrofit2;

import com.github.handioq.baseandroid.logic.network.converter.NullOnEmptyConverterFactory;
import com.github.handioq.baseandroid.logic.network.retrofit2.request.Retrofit2SpiceRequest;
import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Converter;
import retrofit2.Retrofit;

public abstract class Retrofit2SpiceService extends SpiceService {

    private Map<Class<?>, Object> retrofitInterfaceToServiceMap = new HashMap<>();
    private Retrofit.Builder builder;
    private Retrofit restAdapter;
    protected List<Class<?>> retrofitInterfaceList = new ArrayList<>();
    private List<Converter.Factory> mConverters;

    @Override
    public void onCreate() {
        super.onCreate();
        builder = createRestAdapterBuilder();
        restAdapter = builder.build();
    }

    protected abstract String getServerUrl();

    protected Retrofit.Builder createRestAdapterBuilder() {
        Retrofit.Builder builder = new Retrofit.Builder();
        for (Converter.Factory factory : getConverterFactories()) {
            builder.addConverterFactory(factory);
        }
        builder.baseUrl(getServerUrl());
        return builder;
    }

    protected abstract List<Converter.Factory> createConvertersList();

    protected final List<Converter.Factory> getConverterFactories() {
        if (mConverters == null) {
            mConverters = createConvertersList();
            mConverters.add(new NullOnEmptyConverterFactory());
        }
        return mConverters;
    }

    @SuppressWarnings("unchecked")
    protected <T> T getRetrofitService(Class<T> serviceClass) {
        T service = (T) retrofitInterfaceToServiceMap.get(serviceClass);
        if (service == null) {
            service = restAdapter.create(serviceClass);
            retrofitInterfaceToServiceMap.put(serviceClass, service);
        }
        return service;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void addRequest(CachedSpiceRequest<?> request, Set<RequestListener<?>> listRequestListener) {
        if (request.getSpiceRequest() instanceof Retrofit2SpiceRequest) {
            Retrofit2SpiceRequest retrofitSpiceRequest = (Retrofit2SpiceRequest) request.getSpiceRequest();
            retrofitSpiceRequest.setService(getRetrofitService(retrofitSpiceRequest.getRetrofitedInterfaceClass()));
            retrofitSpiceRequest.setCacheManager(getCacheManager());
        }
        super.addRequest(request, listRequestListener);
    }

    protected abstract CacheManager getCacheManager();

    public final List<Class<?>> getRetrofitInterfaceList() {
        return retrofitInterfaceList;
    }

    protected void addRetrofitInterface(Class<?> serviceClass) {
        retrofitInterfaceList.add(serviceClass);
    }
}