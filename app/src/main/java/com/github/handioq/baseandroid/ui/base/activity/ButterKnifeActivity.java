package com.github.handioq.baseandroid.ui.base.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class ButterKnifeActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResId());
        ButterKnife.bind(this);
        afterInject(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    protected void afterInject(Bundle savedInstanceState) {
    }

    @LayoutRes
    protected abstract int getResId();
}