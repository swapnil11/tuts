package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.services.ExampleService;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
             Toast.makeText(MainActivity.this,
                    "My Result Data You passed in :"+intent.getStringExtra("data")+
                    " with timestamp: "+ Calendar.getInstance().getTimeInMillis(),Toast.LENGTH_SHORT).show();
             }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(receiver,new IntentFilter("sampleFilter"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(receiver);
    }

    public void launchService(View view) {
        Intent intent = new Intent(MainActivity.this,ExampleService.class);
        intent.putExtra("data","ACTIVITY DATA");
        startService(intent);
    }
}
