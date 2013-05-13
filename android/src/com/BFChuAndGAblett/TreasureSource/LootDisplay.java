/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.Arrays;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 */
public class LootDisplay extends Activity {

    private static final String TAG = "LootDisplay";
    private ListView lootListView;
    private ArrayList<LootOutListItem> lootItemList;
    private ArrayAdapter<LootOutListItem> arrayAdapter;
    private LootDB lootDB;
    private Cursor lootCursor;
    
    
    private Intent getLootRules = getIntent();
    private int aPL = getLootRules.getIntExtra("aPL", 0);
    private int eCR = getLootRules.getIntExtra("eCR", 0);
    private int enDifficulty = getLootRules.getIntExtra("enDifficulty", 0);
    private int lootSize = getLootRules.getIntExtra("lootSize", 0);
    private int magicLv = getLootRules.getIntExtra("magicLv", 0);
    private double resGold = getLootRules.getDoubleExtra("resGold", 0.0);
    private boolean rollMundane = getLootRules.getBooleanExtra("rollMundane", false);
    private boolean rollGoods = getLootRules.getBooleanExtra("rollGoods", false);
    private boolean noRepeats = getLootRules.getBooleanExtra("noRepeats", false);
    private boolean limitValByCR = getLootRules.getBooleanExtra("limByEV", false);
    private boolean[] itemRestrictions = getLootRules.getBooleanArrayExtra("itemRestrictions");
    private boolean[] displayOpts = getLootRules.getBooleanArrayExtra("displayOpts");
    
    private LootPrefs lootPrefs = new LootPrefs();
    lootPrefs(aPL, eCR, enDifficulty, lootSize, magicLv, resGold, rollMundane, rollGoods, noRepeats, limitValByCR, itemRestrictions, displayOpts);
    
    

    
    
    
    
    //    private boolean 
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loot_display);


        lootListView = (ListView) findViewById(R.id.lootListView); // XML lookup
        lootItemList = new ArrayList<LootOutListItem>();

        arrayAdapter = new ArrayAdapter<LootOutListItem>(this,
                android.R.layout.simple_list_item_1, lootItemList);
        Log.d(TAG, "lootListView: " + lootListView);
        lootListView.setAdapter(arrayAdapter);

        // Database code
        lootDB = new LootDB(this);
        lootDB.open();

        // add test entries to database
        // replace this part with lootRoller logic (LootBuilder)
        lootDB.clear("lootOut");
        lootDB.saveEntry("lootOut", null, 1, 1, "Robe of Stars", 58000.00,
                true, true);
        lootDB.saveEntry("lootOut", null, 11, 1, "Robe of gates", 64000.00,
                true, true);
        lootDB.saveEntry("lootOut", null, 16, 1, "Otherworldly kimono",
                67000.00, true, true);
        lootDB.saveEntry("lootOut", null, 21, 1,
                "Bodywrap of mighty strikes +5", 75000.00, true, true);
        lootDB.saveEntry("lootOut", null, 41, 1,
                "Resplendent robe of the thespian", 75000.00, true, true);
        lootDB.saveEntry("lootOut", null, 52, 1, "Robe of the archmagi",
                75000.00, true, true);
        lootDB.saveEntry("lootOut", null, 68, 4, "Adamantine",
                "Bodywrap of mighty strikes +6", 108000.00, true, true);
        lootDB.saveEntry("lootOut", null, 78, 1, "Robe of eyes", 120000.00,
                true, true);
        lootDB.saveEntry("lootOut", null, 98, 1,
                "Bodywrap of mighty strikes +7", 147000.00, true, true);

        populateLootDisplay();
    }

    private void populateLootDisplay() {
        // get all the Entries

        lootCursor = lootDB.getLootOut();
        startManagingCursor(lootCursor);

        updateLootDisplay();
    }

    private void updateLootDisplay() {
        // 1
        lootCursor.requery();

        // 2
        lootItemList.clear();

        // 3 - if there is data, iterate
        if (lootCursor.moveToFirst()) {
            do {
                Integer id = lootCursor.getInt(0); // make sure you have the
                                                   // order correct
                int dRoll = lootCursor.getInt(1);
                int quantity = lootCursor.getInt(2);
                String specials = lootCursor.getString(3);
                String itemName = lootCursor.getString(4);
                double value = lootCursor.getDouble(5);
                boolean dispGold = true;
                boolean dispRoll = false;
                if (lootCursor.getInt(6) != 1) {
                    dispGold = false;
                }
                if (lootCursor.getInt(7) != 0) {
                    dispRoll = true;
                }

                Log.d(TAG, "\nid: " + id + "\ndRoll: " + dRoll + "\nQuantity"
                        + quantity + "\nSpecials: " + specials + "\nitemName: "
                        + itemName + "\nvalue: " + value);

                LootOutListItem item = new LootOutListItem(id, dRoll, quantity,
                        specials, itemName, value, dispGold, dispRoll);
                lootItemList.add(item);
            } while (lootCursor.moveToNext());
        }

        // 4
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.loot, menu);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void onReturnButton(View v) {
        finish();
    }
}
