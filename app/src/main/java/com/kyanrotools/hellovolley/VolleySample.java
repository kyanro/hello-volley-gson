package com.kyanrotools.hellovolley;

import android.app.Application;
import android.content.Context;

/**
 * Created by ppp on 2014/08/27.
 */
public class VolleySample extends Application {
    private static VolleySample mInstance;
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mAppContext = getApplicationContext();
    }

    synchronized public static VolleySample getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
