package com.github.handioq.baseandroid.ui.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ProgressBar;

import com.github.handioq.baseandroid.R;
import com.github.handioq.baseandroid.logic.SpiceHolder;
import com.github.handioq.baseandroid.logic.network.AppRetrofitRestService;
import com.github.handioq.baseandroid.logic.network.AppSpiceManager;
import com.github.handioq.baseandroid.ui.base.callback.ProgressOwner;

import butterknife.BindView;

public abstract class BaseActivity extends ButterKnifeActivity implements SpiceHolder, ProgressOwner {

    private AppSpiceManager spiceManager = new AppSpiceManager(AppRetrofitRestService.class);
    //private MaterialDialog progressDialog;

    @Nullable
    @BindView(R.id.progress)
    protected ProgressBar progress;

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    @Override
    protected void afterInject(Bundle savedInstanceState) {
        super.afterInject(savedInstanceState);
    }

    public AppSpiceManager getSpiceManager() {
        return spiceManager;
    }

    @Override
    public void showProgressDialog(@StringRes int text) {
        //progressDialog = DialogsHelper.buildProgressDialog(this, R.string.dialog_please_wait, text);
    }

    public void showProgressDialog(String content) {
        //progressDialog = DialogsHelper.buildProgressDialog(this, R.string.dialog_please_wait, content);
    }

    @Override
    public void showProgress() {
        if (progress != null) {
            progress.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgress() {
        /*if (progressDialog != null) {
            //progressDialog.dismiss();
        }*/
        if (progress != null) {
            progress.setVisibility(View.GONE);
        }
    }
}