package com.github.handioq.baseandroid.logic.request;

import com.github.handioq.baseandroid.logic.Api;
import com.github.handioq.baseandroid.model.dto.FaqResponse;

import retrofit2.Call;

public class FaqRequest extends CommonRequest<FaqResponse> {

    public FaqRequest() {
        super(FaqResponse.class);
    }

    @Override
    protected Call<FaqResponse> prepare(Api api) {
        return api.getFaq();
    }
}
