package com.github.handioq.baseandroid.logic;

import android.support.annotation.NonNull;

import com.github.handioq.baseandroid.logic.request.FaqRequest;
import com.github.handioq.baseandroid.model.dto.FaqResponse;
import com.github.handioq.baseandroid.logic.network.listener.CommonCallback;

public class DataManager {

    public static <SPICE extends SpiceHolder> void getFaq(@NonNull final SPICE holder, CommonCallback.ResultListener<FaqResponse> listener) {
        holder.getSpiceManager().execute(new FaqRequest(), new CommonCallback<>(listener));
    }

    public static <SPICE extends SpiceHolder> void getFaq2(@NonNull final SPICE holder, CommonCallback.ResultListener<FaqResponse> listener) {
        holder.getSpiceManager().execute(new FaqRequest(), new CommonCallback<>(listener));
    }

}
