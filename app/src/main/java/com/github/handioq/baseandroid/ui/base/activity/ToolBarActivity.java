package com.github.handioq.baseandroid.ui.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.handioq.baseandroid.R;

import butterknife.BindView;

public abstract class ToolBarActivity extends BaseActivity {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @BindView(R.id.toolbarContainer)
    ViewGroup toolbarContainer;

    @Override
    protected void afterInject(Bundle savedInstanceState) {
        super.afterInject(savedInstanceState);

        setupToolbar();
    }

    private void setupToolbar() {
        /*if (toolbarContainer != null) {
            View toolbarView = View.inflate(this, getToolbarLayoutId(), null);
            toolbarContainer.addView(toolbarView);
            toolbar = (Toolbar) toolbarContainer.findViewById(R.id.toolbar);
        }

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
                supportActionBar.setDefaultDisplayHomeAsUpEnabled(true);
            }
        }*/
    }

    @Nullable
    protected Toolbar getToolbar() {
        return toolbar;
    }

    protected void setToolbarTitle(String title) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(title);
        }
    }

    protected void setToolbarTitle(@StringRes int title) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(title);
        }
    }

    protected int getToolbarLayoutId() {
        //return R.layout.toolbar;
        return 0;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
