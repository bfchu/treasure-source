package com.BFChuAndGAblett.TreasureSource;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.CheckBox;

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
/*
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
*/
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


        EditText et_aPL = (EditText) findViewById(R.id.editText1);
        int aPL = Integer.parseInt(et_aPL.getText().toString());
        g.putExtra("aPL", aPL);
        
        EditText et_eD = (EditText) findViewById(R.id.editText2);
        int eCR = Integer.parseInt(et_eD.getText().toString());
        g.putExtra("eCR", eCR);
        
        RadioGroup rg_dS = (RadioGroup) findViewById(R.id.difficulty_radioGroup);
        int enDifficulty = rg_dS.getCheckedRadioButtonID();
        //Switch Statement to determine what to use
        g.putExtra("enDifficulty", enDifficulty);
        
        RadioGroup rg_tS = (RadioGroup) findViewById(R.id.size_radioGroup);
        int lootSize = rg_tS.getCheckedRadioButtonID();
        //Switch Statement to determine what to use
        //Switch statement potentially actually handled in LootDisplay or LootCalc from theres
        g.putExtra("lootSize", lootSize);
        
        RadioGroup rg_mL = (RadioGroup) findViewById(R.id.magic_level_radioGroup);
        int magicLv = rg_mL.getCheckedRadioButtonID();
        //Switch Statement to determine what to use
        g.putExtra("magicLv", magicLv);
        
        CheckBox cb_rMun = (Checkbox) findViewById(R.id.checkBox1);
        boolean rollMundane = cb_rMun.isChecked();
        g.putExtra("rollMundane", rollMundane);
        
        CheckBox cb_rGoods = (Checkbox) findViewById(R.id.checkBox2);
        boolean rollGoodss = cb_rGoods.isChecked();
        g.putExtra("rollGoods", rollGoods);
        
        CheckBox cb_discD = (Checkbox) findViewById(R.id.checkBox3);
        boolean noRepeats = cb_DiscD.isChecked();
        g.putExtra("noRepeats", noRepeats);
        
        CheckBox cb_limByEV = (Checkbox) findViewById(R.id.checkBox4);
        boolean limitValByCR = cb_limByEV.isChecked();

        boolean[] itemRestrictions = new boolean[9];
        CheckBox cb_ignoreArmor = (Checkbox) findViewById(R.id.checkBox5);
        itemRestrictions[0] = cb_ignoreArmor.isChecked();
        CheckBox cb_ignoreWeapons = (Checkbox) findViewById(R.id.checkBox6);
        itemRestrictions[1] = cb_ignoreWeapons.isChecked();
        CheckBox cb_ignorePotions = (Checkbox) findViewById(R.id.checkBox7);
        itemRestrictions[2] = cb_ignorePotions.isChecked();
        CheckBox cb_ignoreRings = (Checkbox) findViewById(R.id.checkBox8);
        itemRestrictions[3] = cb_ignoreRings.isChecked();
        CheckBox cb_ignoreRods = (Checkbox) findViewById(R.id.checkBox9);
        itemRestrictions[4] = cb_ignoreRods.isChecked();
        CheckBox cb_ignoreScrolls = (Checkbox) findViewById(R.id.checkBox10);
        itemRestrictions[5] = cb_ignoreScrolls.isChecked();
        CheckBox cb_ignoreStaves = (Checkbox) findViewById(R.id.checkBox11);
        itemRestrictions[6] = cb_ignoreStaves.isChecked();
        CheckBox cb_ignoreWands = (Checkbox) findViewById(R.id.checkBox12);
        itemRestrictions[7] = cb_ignoreWands.isChecked();
        CheckBox cb_ignoreWondrous = (Checkbox) findViewById(R.id.checkBox13);
        itemRestrictions[8] = cb_ignoreWondrous.isChecked();
        
        boolean[] displayOpts = new boolean[3]
        CheckBox cb_displayGold = (Checkbox) findViewById(R.id.checkBox14);
        displayOpts[0] = cb_displayGold.isChecked();
        CheckBox cb_displayChance = (Checkbox) findViewById(R.id.checkBox15);
        displayOpts[1] = cb_displayChance.isChecked();
        CheckBox cb_displayTotal = (Checkbox) findViewById(R.id.checkBox16);
        displayOpts[2] = cb_displayTotal.isChecked();
        

        
        
/*        
         * Something about retrieving the radio button name and then uses a
         * switch statement to search by buttonName or some recorded buttonValue
         * for the desired float value to pass?(If Button Value exists, use?)
         
        SharedPreferences mySharedPreferences = getSharedPreferences(
                "MY_PREFS", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt("Apl", aPL);
        editor.putInt("EncounterDifficulty", eD);
//        editor.putFloat("TreasureSize", 0.5f);
//        editor.putInt("magicLevel", 1);
        editor.putBoolean("rollMundane", rMun);
        editor.putBoolean("rollGoods", rGoods);
        editor.putBoolean("throwAwayDuplicates", discD);
        editor.putBoolean("limitItemValue", limByEV);
        editor.putBoolean("restrictArmor", ignoreArmor);
        editor.putBoolean("restrictWeapons", ignoreWeapons);
        editor.putBoolean("restrictPotions", ignorePotions);
        editor.putBoolean("restrictRings", ignoreRings);
        editor.putBoolean("restrictRods", ignoreRods);
        editor.putBoolean("restrictScrolls", ignoreScrolls);
        editor.putBoolean("restrictStaves", ignoreStaves);
        editor.putBoolean("restrictWands", ignoreWands);
        editor.putBoolean("restrictWondrous", ignoreWondrous);
        editor.putBoolean("displayValue", displayGold);
        editor.putBoolean("displayLootRoll", displayChance);
        editor.putBoolean("displayHoardValue", displayTotal);
        editor.commit();*/


        startActivity(g);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in onRollLootButton()");
        }
    }
}
