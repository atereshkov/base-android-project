package com.github.handioq.baseandroid.ui.catalog;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.handioq.baseandroid.R;
import com.github.handioq.baseandroid.logic.DataManager;
import com.github.handioq.baseandroid.logic.network.listener.CommonCallback;
import com.github.handioq.baseandroid.model.dto.FaqItem;
import com.github.handioq.baseandroid.model.dto.FaqResponse;
import com.github.handioq.baseandroid.ui.base.activity.ToolBarActivity;
import com.github.handioq.baseandroid.ui.catalog.adapter.CatalogAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CatalogActivity extends ToolBarActivity implements CommonCallback.ResultListener<FaqResponse> {

    public static final String TAG = CatalogActivity.class.getSimpleName();
    @BindView(R.id.rv_catalog)
    RecyclerView rvCatalog;

    private CatalogAdapter adapter;

    @Override
    protected int getResId() {
        return R.layout.activity_catalog;
    }

    @Override
    protected void afterInject(Bundle savedInstanceState) {
        super.afterInject(savedInstanceState);
        setToolbarTitle("Catalog");
        initViews();

        DataManager.getFaq(this, this);
        showProgress();
    }

    private void initViews() {
        rvCatalog.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onResultSuccess(FaqResponse response) {
        hideProgress();
        List<FaqItem> items = new ArrayList<>(response.getItems());
        if (adapter == null) {
            adapter = new CatalogAdapter(items);
            rvCatalog.setAdapter(adapter);
        } else {
            adapter.update(items);
        }
    }

    @Override
    public void onResultFailure(String error) {
        hideProgress();
        Log.e(TAG, error);
    }

}
