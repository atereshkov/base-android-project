package com.github.handioq.baseandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.github.handioq.baseandroid.logic.DataManager;
import com.github.handioq.baseandroid.model.dto.FaqResponse;
import com.github.handioq.baseandroid.logic.network.listener.CommonCallback;
import com.github.handioq.baseandroid.ui.base.activity.ToolBarActivity;
import com.github.handioq.baseandroid.ui.catalog.CatalogActivity;

import butterknife.OnClick;

public class MainActivity extends ToolBarActivity implements CommonCallback.ResultListener<FaqResponse> {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected int getResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterInject(Bundle savedInstanceState) {
        super.afterInject(savedInstanceState);
        setToolbarTitle("Main");
        initViews();
    }

    private void initViews() {

    }

    @OnClick(R.id.button1)
    public void onButtonClick() {
        showProgress();
        DataManager.getFaq2(this, this);
    }

    @OnClick(R.id.open_catalog)
    public void onOpenCatalogClick() {
        Intent intent = new Intent(this, CatalogActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResultSuccess(FaqResponse response) {
        hideProgress();
        Log.e(TAG, "onResultSuccess");
    }

    @Override
    public void onResultFailure(String error) {
        hideProgress();
        Log.e(TAG, error);
    }


}
