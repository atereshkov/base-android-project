package com.github.handioq.baseandroid.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonHelper {
    private static Gson gson;
    private static Gson nullableGson;

    static {
        gson = new Gson();
        nullableGson = new GsonBuilder().serializeNulls().create();
    }

    public static Gson getNullableGson() {
        return nullableGson;
    }

    public static Gson getGson() {
        return gson;
    }
}
