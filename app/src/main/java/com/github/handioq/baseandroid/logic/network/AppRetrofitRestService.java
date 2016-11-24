package com.github.handioq.baseandroid.logic.network;

import android.app.Application;

import com.github.handioq.baseandroid.BuildConfig;
import com.github.handioq.baseandroid.Constants;
import com.github.handioq.baseandroid.logic.Api;
import com.github.handioq.baseandroid.logic.network.retrofit2.AppRequestRunner;
import com.github.handioq.baseandroid.logic.network.retrofit2.Retrofit2GsonSpiceService;
import com.octo.android.robospice.networkstate.NetworkStateChecker;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.request.RequestProcessor;
import com.octo.android.robospice.request.RequestProgressManager;
import com.octo.android.robospice.request.RequestRunner;
import com.octo.android.robospice.request.notifier.RequestListenerNotifier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;


public class AppRetrofitRestService extends Retrofit2GsonSpiceService {

    private CacheManager cacheManager;

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(Api.class);
        //addRetrofitInterface(CouponsApi.class);
    }

    @Override
    protected String getServerUrl() {
        return Constants.BACKEND_ENDPOINT;
    }


    @Override
    protected Retrofit.Builder createRestAdapterBuilder() {
        OkHttpClient client = prepareOkClient(new OkHttpClient.Builder());
        Retrofit.Builder builder = super.createRestAdapterBuilder();
        builder.client(client);
        return builder;
    }

    protected OkHttpClient prepareOkClient(OkHttpClient.Builder builder) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor)
                .addInterceptor(new TokenIntercepter())
                .addInterceptor(new NetworkInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        return builder.build();
    }

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        cacheManager = new CacheManager();
        /*RoboSpiceDatabaseHelper databaseHelper = new DatabaseHelper(application);
        LayPersisterFactory inDatabaseObjectPersisterFactory = new LayPersisterFactory(application, databaseHelper, LayContract.getUris());
        cacheManager.addPersister(inDatabaseObjectPersisterFactory);
        return cacheManager;*/
        // todo?
        return cacheManager;
    }

    @Override
    protected CacheManager getCacheManager() {
        return cacheManager;
    }

    @Override
    protected RequestProcessor createRequestProcessor(CacheManager cacheManager, RequestProgressManager requestProgressManager, RequestRunner requestRunner) {
        requestRunner = createRequestRunner(cacheManager, getExecutorService(), getNetworkStateChecker(), requestProgressManager);
        return super.createRequestProcessor(cacheManager, requestProgressManager, requestRunner);
    }

    private RequestRunner createRequestRunner(CacheManager cacheManager, final ExecutorService executorService, final NetworkStateChecker networkStateChecker, RequestProgressManager requestProgressManager) {
        return new AppRequestRunner(getApplicationContext(), cacheManager, executorService, requestProgressManager, networkStateChecker);
    }

    @Override
    protected RequestListenerNotifier createRequestRequestListenerNotifier() {
        return super.createRequestRequestListenerNotifier();
    }
}
