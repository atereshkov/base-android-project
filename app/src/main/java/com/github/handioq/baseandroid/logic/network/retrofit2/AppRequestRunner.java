package com.github.handioq.baseandroid.logic.network.retrofit2;

import android.content.Context;

import com.octo.android.robospice.networkstate.NetworkStateChecker;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.request.DefaultRequestRunner;
import com.octo.android.robospice.request.RequestProgressManager;

import java.util.concurrent.ExecutorService;

public class AppRequestRunner extends DefaultRequestRunner {
    public AppRequestRunner(Context context, CacheManager cacheManager, ExecutorService executorService, RequestProgressManager requestProgressBroadcaster, NetworkStateChecker networkStateChecker) {
        super(context, cacheManager, executorService, requestProgressBroadcaster, networkStateChecker);
    }
}
