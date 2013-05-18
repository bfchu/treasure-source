/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Brian Chu and Garrick Ablett
 *
 */
public class SplashActivity extends Activity {

    private static String TAG = SplashActivity.class.getName();
    private static long SLEEP_TIME = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in SpashActivity onCreate()");
        }

        // // Get Full-screen mode?
        // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        // WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // ProgressDialog progress = new ProgressDialog(this);
        // ProgressDialog.show(this, "Loading database", "Please wait");

        // Check if the database has been initialized before, or if there is an
        // update
        SharedPreferences mySharedPreferences = getSharedPreferences(
                "Run_Once_Prefs", Activity.MODE_PRIVATE);

        int lastVersionCode = mySharedPreferences.getInt("lastVersionCode", 0); // the
                                                                                // last
                                                                                // time
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "lastVersionCode:" + lastVersionCode);
        }

        int versionCode = 0;
        try {
            versionCode = getPackageManager().getPackageInfo(getPackageName(),
                    0).versionCode;
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "versionCode:" + versionCode);
        }

        if (lastVersionCode == 0) {

            Intent dbService = new Intent(this, DatabaseLoaderService.class);
            try {
                startService(dbService);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }

            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putInt("lastVersionCode", versionCode);
            editor.commit();

        } else {
            Toast.makeText(this, "Skipped initializing database",
                    Toast.LENGTH_LONG).show();

            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putInt("lastVersionCode", versionCode);
            editor.commit();
        }

        // // Start timer and launch main activity
        // try {
        // // Sleeping
        // Thread.sleep(SLEEP_TIME * 1000);
        // } catch (Exception e) {
        // Log.e(TAG, e.getMessage());
        // }

        // progress.dismiss();

        // Start main activity
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finish();
    }

}
