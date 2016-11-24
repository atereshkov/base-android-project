package com.github.handioq.baseandroid.ui.base.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ProgressBar;

import com.github.handioq.baseandroid.R;
import com.github.handioq.baseandroid.logic.SpiceHolder;
import com.github.handioq.baseandroid.logic.network.AppSpiceManager;
import com.github.handioq.baseandroid.ui.base.callback.ProgressOwner;

import butterknife.BindView;

public abstract class BaseFragment extends ButterKnifeFragment implements SpiceHolder, ProgressOwner {

    private SpiceHolder holder;

    @Nullable
    @BindView(R.id.progress)
    protected ProgressBar progress;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        holder = (SpiceHolder) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        holder = null;
    }

    @Override
    public AppSpiceManager getSpiceManager() {
        return holder.getSpiceManager();
    }

    public void showProgressDialog() {
        if (progress != null) {
            progress.setVisibility(View.VISIBLE);
        }
    }

    public void showProgressDialog(String ignore) {
        if (progress != null) {
            progress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showProgress() {
        if (progress != null) {
            progress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showProgressDialog(@StringRes int ignore) {
        if (progress != null) {
            progress.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgress() {
        if (progress != null) {
            progress.setVisibility(View.GONE);
        }
    }
}
