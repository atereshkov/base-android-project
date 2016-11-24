package com.github.handioq.baseandroid.ui.base.callback;

import android.support.annotation.StringRes;

public interface ProgressOwner {

    void showProgressDialog(String text);

    void showProgressDialog(@StringRes int text);

    void showProgress();

    void hideProgress();

}
