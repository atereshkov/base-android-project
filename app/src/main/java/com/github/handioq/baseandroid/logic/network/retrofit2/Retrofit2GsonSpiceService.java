package com.github.handioq.baseandroid.logic.network.retrofit2;

import android.app.Application;

import com.github.handioq.baseandroid.utils.GsonHelper;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class Retrofit2GsonSpiceService extends Retrofit2SpiceService {
    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        return new CacheManager();
    }

    @Override
    protected List<Converter.Factory> createConvertersList() {
        List<Converter.Factory> factories = new ArrayList<>();
        factories.add(GsonConverterFactory.create(GsonHelper.getNullableGson()));
        return factories;
    }

}
