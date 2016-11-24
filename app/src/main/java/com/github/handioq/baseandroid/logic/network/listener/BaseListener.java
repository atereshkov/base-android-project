package com.github.handioq.baseandroid.logic.network.listener;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public abstract class BaseListener<RESULT> implements RequestListener<RESULT> {
    @Override
    public void onRequestFailure(SpiceException spiceException) {
        onFailure(spiceException);
        onDone();
    }

    @Override
    public void onRequestSuccess(RESULT result) {
        onSuccess(result);
        onDone();
    }

    public abstract void onSuccess(RESULT response);

    public void onFailure(Throwable t) {
        onFailure(t.getMessage());
    }

    public abstract void onFailure(String t);

    protected void onDone() {

    }
}
