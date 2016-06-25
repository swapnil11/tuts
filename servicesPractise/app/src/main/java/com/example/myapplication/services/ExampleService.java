package com.example.myapplication.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by srg11 on 6/25/2016.
 */
public class ExampleService extends IntentService {

    public ExampleService() {
        super("ExampleService");
    }

    @Override
    protected void onHandleIntent(final Intent intent) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent sendIntent = new Intent("sampleFilter");
        sendIntent.putExtra("data", intent.getStringExtra("data"));
        LocalBroadcastManager.getInstance(ExampleService.this).sendBroadcast(sendIntent);

    }
}
