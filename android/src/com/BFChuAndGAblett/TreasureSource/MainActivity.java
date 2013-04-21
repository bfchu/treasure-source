package com.BFChuAndGAblett.TreasureSource;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

/**
 * @author Brian F. Chu, Garrick S. Ablett
 * @version 0.1
 * 
 */
public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in onCreate()");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in onStart()");
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in onRestart()");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in onResume()");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in onPause()");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in onStop()");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in onDestroy()");
        }
    }

    public void onRollLootButton(View v) {
        Intent g = new Intent(this, LootDisplay.class);
        startActivity(g);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in onRollLootButton()");
        }
    }
}
