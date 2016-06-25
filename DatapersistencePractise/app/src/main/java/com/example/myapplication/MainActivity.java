package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.adapter.CustomCursorAdapter;
import com.example.myapplication.helper.SampleDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private CustomCursorAdapter customAdapter;
    private SampleDatabaseHelper databaseHelper;
    private ListView listView;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new SampleDatabaseHelper(this);

        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "clicked on item: " + position);
            }
        });
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                customAdapter = new CustomCursorAdapter(MainActivity.this, databaseHelper.getAllData());
                listView.setAdapter(customAdapter);
                insertData();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void insertData() {
        databaseHelper.insertData("A","B");
        databaseHelper.insertData("B","C");
        databaseHelper.insertData("C","D");
        databaseHelper.insertData("D","E");
        databaseHelper.insertData("E","F");
        databaseHelper.insertData("F","G");
        databaseHelper.insertData("G","H");
        databaseHelper.insertData("H","I");
        databaseHelper.insertData("I","J");
        databaseHelper.insertData("J","K");
        customAdapter.changeCursor(databaseHelper.getAllData());
    }
}
