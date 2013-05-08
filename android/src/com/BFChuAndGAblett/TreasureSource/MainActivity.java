package com.BFChuAndGAblett.TreasureSource;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

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

        // if (BuildConfig.DEBUG) {
        // Log.d(TAG, "in onCreate()");
        // }

        // store create SharedPreferences

        // get/create the preferences
        SharedPreferences mySharedPreferences = getSharedPreferences(
                "MY_PREFS", Activity.MODE_PRIVATE);

        // get a preferences editor
        SharedPreferences.Editor editor = mySharedPreferences.edit();

        // store primitive values
        editor.putInt("Apl", 1);
        editor.putInt("EncounterDifficulty", 1);
        editor.putFloat("TreasureSize", 0.5f);
        editor.putInt("magicLevel", 1);
        editor.putBoolean("rollMundane", true);
        editor.putBoolean("rollGoods", true);
        editor.putBoolean("throwAwayDuplicates", false);
        editor.putBoolean("limitItemValue", false);
        editor.putBoolean("restrictArmor", false);
        editor.putBoolean("restrictWeapons", false);
        editor.putBoolean("restrictPotions", false);
        editor.putBoolean("restrictRings", false);
        editor.putBoolean("restrictRods", false);
        editor.putBoolean("restrictScrolls", false);
        editor.putBoolean("restrictStaves", false);
        editor.putBoolean("restrictWands", false);
        editor.putBoolean("restrictWondrous", false);
        editor.putBoolean("displayValue", true);
        editor.putBoolean("displayLootRoll", false);
        editor.putBoolean("displayHoardValue", false);
        // editor.putLong("aNumber", 31);
        // editor.putString("textEntryValue", "Not Empty");

        // commit changes
        editor.commit();

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

        EditText et_aPL = (EditText) findViewById(R.id.editText1);
        int aPL = Integer.parseInt(et_aPL.getText().toString());

        EditText et_eD = (EditText) findViewById(R.id.editText2);
        int eD = Integer.parseInt(et_eD.getText().toString());

        /*
         * Something about retrieving the radio button name and then uses a
         * switch statement to search by buttonName or some recorded buttonValue
         * for the desired float value to pass?(If Button Value exists, use?)
         */
        SharedPreferences mySharedPreferences = getSharedPreferences(
                "MY_PREFS", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt("Apl", aPL);
        editor.putInt("EncounterDifficulty", eD);
        editor.putFloat("TreasureSize", 0.5f);
        editor.putInt("magicLevel", 1);
        editor.putBoolean("rollMundane", true);
        editor.putBoolean("rollGoods", true);
        editor.putBoolean("throwAwayDuplicates", false);
        editor.putBoolean("limitItemValue", false);
        editor.putBoolean("restrictArmor", false);
        editor.putBoolean("restrictWeapons", false);
        editor.putBoolean("restrictPotions", false);
        editor.putBoolean("restrictRings", false);
        editor.putBoolean("restrictRods", false);
        editor.putBoolean("restrictScrolls", false);
        editor.putBoolean("restrictStaves", false);
        editor.putBoolean("restrictWands", false);
        editor.putBoolean("restrictWondrous", false);
        editor.putBoolean("displayValue", true);
        editor.putBoolean("displayLootRoll", false);
        editor.putBoolean("displayHoardValue", false);
        editor.commit();

        Intent g = new Intent(this, LootDisplay.class);
        startActivity(g);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in onRollLootButton()");
        }
    }
}
