package com.BFChuAndGAblett.TreasureSource;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * @author Brian F. Chu, Garrick S. Ablett
 * @version 0.1
 * 
 */
public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private static final int TREASURE_SIZE = 2131230737;
    private static final int ENCOUNTER_SIZE = 2131230729;
    private static final int MAGIC_SIZE = 2131230745;
    private static final int CAMPAIGN_SIZE = 2131230751;

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
        initializeFields();
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
        setRules(g);

        startActivity(g);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in onRollLootButton()");
        }
    }

    public void setRules(Intent intent) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in setRules");
        }

        EditText et_aPL = (EditText) findViewById(R.id.editText1);
        int aPL = 0;
        aPL = Integer.parseInt(et_aPL.getText().toString());
        if (et_aPL.getText().toString() != "") { // if the user input anything
            intent.putExtra("aPL", aPL);
        } else {
            intent.putExtra("aPL", 1);
        }

        EditText et_eD = (EditText) findViewById(R.id.editText2);
        int eCR = 0;
        eCR = Integer.parseInt(et_eD.getText().toString());
        if (et_eD.getText().toString() != "") {
            intent.putExtra("eCR", eCR);
        } else {
            intent.putExtra("eCR", 1);
        }

        RadioGroup rg_tS = (RadioGroup) findViewById(R.id.size_radioGroup);
        int lootSize = 0;
        // int lootSizeID = rg_tS.getCheckedRadioButtonId();
        lootSize = rg_tS.getCheckedRadioButtonId() - TREASURE_SIZE;
        // Switch Statement to determine what to use
        // Switch statement potentially actually handled in LootDisplay or
        // LootCalc from theres
        intent.putExtra("lootSize", lootSize);

        RadioGroup rg_dS = (RadioGroup) findViewById(R.id.difficulty_radioGroup);
        int enDifficulty = 0;
        // int enDifficultyID = rg_dS.getCheckedRadioButtonId();
        enDifficulty = rg_dS.getCheckedRadioButtonId() - ENCOUNTER_SIZE;

        // Switch Statement to determine what to use
        // Switch statement potentially actually handled in LootDisplay or
        // LootCalc from theres
        intent.putExtra("enDifficulty", enDifficulty);

        RadioGroup rg_mL = (RadioGroup) findViewById(R.id.magic_level_radioGroup);
        int magicLv = 0;
        // int magicLvID = rg_mL.getCheckedRadioButtonId();
        magicLv = rg_mL.getCheckedRadioButtonId() - MAGIC_SIZE;
        intent.putExtra("magicLv", magicLv);
        // Switch Statement to determine what to use

        EditText et_rG = (EditText) findViewById(R.id.editText3);
        double resGold = 0.0;
        resGold = Double.parseDouble(et_rG.getText().toString());
        if (et_rG.getText().toString() != "") {
            intent.putExtra("resGold", resGold);
        } else {
            intent.putExtra("resGold", 50.0);
        }

        CheckBox cb_rMun = (CheckBox) findViewById(R.id.checkBox1);
        boolean rollMundane = false;
        rollMundane = cb_rMun.isChecked();
        intent.putExtra("rollMundane", rollMundane);

        CheckBox cb_rGoods = (CheckBox) findViewById(R.id.checkBox2);
        boolean rollGoods = false;
        rollGoods = cb_rGoods.isChecked();
        intent.putExtra("rollGoods", rollGoods);

        CheckBox cb_discD = (CheckBox) findViewById(R.id.checkBox3);
        boolean noRepeats = false;
        noRepeats = cb_discD.isChecked();
        intent.putExtra("noRepeats", noRepeats);

        CheckBox cb_limByEV = (CheckBox) findViewById(R.id.checkBox4);
        boolean limitValByCR = false;
        limitValByCR = cb_limByEV.isChecked();
        intent.putExtra("limitValByCR", limitValByCR);

        boolean[] itemRestrictions = new boolean[12];
        itemRestrictions[0] = false;
        itemRestrictions[1] = false;
        itemRestrictions[2] = false;
        CheckBox cb_ignoreArmor = (CheckBox) findViewById(R.id.checkBox5);
        itemRestrictions[3] = cb_ignoreArmor.isChecked();
        CheckBox cb_ignoreWeapons = (CheckBox) findViewById(R.id.checkBox6);
        itemRestrictions[4] = cb_ignoreWeapons.isChecked();
        CheckBox cb_ignorePotions = (CheckBox) findViewById(R.id.checkBox7);
        itemRestrictions[5] = cb_ignorePotions.isChecked();
        CheckBox cb_ignoreRings = (CheckBox) findViewById(R.id.checkBox8);
        itemRestrictions[6] = cb_ignoreRings.isChecked();
        CheckBox cb_ignoreRods = (CheckBox) findViewById(R.id.checkBox9);
        itemRestrictions[7] = cb_ignoreRods.isChecked();
        CheckBox cb_ignoreScrolls = (CheckBox) findViewById(R.id.checkBox10);
        itemRestrictions[8] = cb_ignoreScrolls.isChecked();
        CheckBox cb_ignoreStaves = (CheckBox) findViewById(R.id.checkBox11);
        itemRestrictions[9] = cb_ignoreStaves.isChecked();
        CheckBox cb_ignoreWands = (CheckBox) findViewById(R.id.checkBox12);
        itemRestrictions[10] = cb_ignoreWands.isChecked();
        CheckBox cb_ignoreWondrous = (CheckBox) findViewById(R.id.checkBox13);
        itemRestrictions[11] = cb_ignoreWondrous.isChecked();
        intent.putExtra("itemRestrictions", itemRestrictions);

        boolean[] displayOpts = new boolean[3];
        CheckBox cb_displayGold = (CheckBox) findViewById(R.id.checkBox14);
        displayOpts[0] = cb_displayGold.isChecked();
        CheckBox cb_displayChance = (CheckBox) findViewById(R.id.checkBox15);
        displayOpts[1] = cb_displayChance.isChecked();
        CheckBox cb_displayTotal = (CheckBox) findViewById(R.id.checkBox16);
        displayOpts[2] = cb_displayTotal.isChecked();
        intent.putExtra("displayOpts", displayOpts);

        // TODO: Use useClassic in lootPrefs
        CheckBox cb_useClassic = (CheckBox) findViewById(R.id.checkBox17);
        boolean useClassic = false;
        noRepeats = cb_useClassic.isChecked();
        intent.putExtra("useClassic", useClassic);

        // TODO: Use Campaign Speed in lootPrefs
        RadioGroup rg_cS = (RadioGroup) findViewById(R.id.campaign_speed_radioGroup);
        int campSpeed = 0;
        // int campSpeedID = rg_cS.getCheckedRadioButtonId();
        campSpeed = rg_cS.getCheckedRadioButtonId() - CAMPAIGN_SIZE;
        intent.putExtra("campSpeed", campSpeed);
        // Switch Statement to determine what to use

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in setRules: aPL: " + aPL + " eCR: " + eCR
                    + " enDifficulty: " + enDifficulty + " lootSize: "
                    + lootSize + " magicLv " + magicLv + " rollMundane: "
                    + rollMundane + " rollGood: " + rollGoods + "campSpeed"
                    + campSpeed);
        }

    }

    public void initializeFields() {
        EditText TextFieldAPL = (EditText) findViewById(R.id.editText1);
        TextFieldAPL.setText("1");

        EditText TextFieldECR = (EditText) findViewById(R.id.editText2);
        TextFieldECR.setText("1");

        EditText TextFieldResGold = (EditText) findViewById(R.id.editText3);
        TextFieldResGold.setText("10.0");
    }

}
