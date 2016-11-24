package com.github.handioq.baseandroid.model.dto;

import java.util.Collection;

public class FaqResponse extends BaseResponse {

    private Collection<FaqItem> items;

    public Collection<FaqItem> getItems() {
        return items;
    }
}
