package com.ss.nsdc.application;


import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;



/**
 * Created by Mayank on 27/04/2016.
 */


public class NsdcApplication extends Application {

    private static NsdcApplication mInstance;
    private static RequestQueue mRequestQueue;
    public static String TAG = "dummy";


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void registerOnProvideAssistDataListener(OnProvideAssistDataListener callback) {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;



    }


    public static synchronized NsdcApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(this.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request = settingTimeout(request);
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        //   request=settingTimeout(request);
   /*     RetryPolicy retryPolicy = new DefaultRetryPolicy(
                6000000,
                4,
                1000
        );*/
        request.setRetryPolicy(new DefaultRetryPolicy(1000 * 120, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //   request.setRetryPolicy(retryPolicy);
        getRequestQueue().add(request);
    }

    public Request settingTimeout(Request request) {
        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        return request;
    }

    public void cancelPendingRequest(Object tag) {
        getRequestQueue().cancelAll(tag);
    }

}