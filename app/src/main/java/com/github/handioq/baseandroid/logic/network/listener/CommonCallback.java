package com.github.handioq.baseandroid.logic.network.listener;

import android.support.annotation.CallSuper;


public class CommonCallback<RESULT> extends BaseListener<RESULT> {

    private ResultListener<RESULT> listener;

    public CommonCallback() {
    }

    public CommonCallback(ResultListener<RESULT> listener) {
        this.listener = listener;
    }

    @CallSuper
    @Override
    public void onSuccess(RESULT response) {
        if (listener != null) {
            listener.onResultSuccess(response);
        }
    }

    @CallSuper
    @Override
    public void onFailure(String t) {
        if (listener != null) {
            listener.onResultFailure(t);
        }
    }

    @Override
    protected void onDone() {

    }

    public interface ResultListener<RESULT> {
        void onResultSuccess(RESULT result);

        void onResultFailure(String error);
    }
}
