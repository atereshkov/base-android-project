package com.github.handioq.baseandroid.logic.network;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.SpiceService;

public class AppSpiceManager extends SpiceManager {
    /**
     * Creates a {@link SpiceManager}. Typically this occurs in the construction
     * of an Activity or Fragment. This method will check if the service to bind
     * to has been properly declared in AndroidManifest.
     *
     * @param spiceServiceClass the service class to bind to.
     */
    public AppSpiceManager(Class<? extends SpiceService> spiceServiceClass) {
        super(spiceServiceClass);
    }

}
