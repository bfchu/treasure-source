/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loot_display);

        /*
         * // load data from SharedPreferences
         * 
         * // get/create the preferences SharedPreferences mySharedPreferences =
         * getSharedPreferences( "MY_PREFS", Activity.MODE_PRIVATE);
         * 
         * int Apl = mySharedPreferences.getString("Apl", 1); int
         * EncounterDifficulty = mySharedPreferences.getInt(
         * "EncounterDifficulty", 1); float TreasureSize =
         * mySharedPreferences.getFloat("TreasureSize", 0.5f); int magicLevel =
         * mySharedPreferences.getInt("magicLevel", 1); boolean rollMundane =
         * mySharedPreferences.getBoolean("rollMundane", true); boolean
         * rollGoods = mySharedPreferences.getBoolean("rollGoods", true);
         * boolean throwAwayDuplicates = mySharedPreferences.getBoolean(
         * "throwAwayDuplicates", false); boolean limitItemValue =
         * mySharedPreferences.getBoolean( "limitItemValue", false); boolean
         * restrictArmor = mySharedPreferences.getBoolean("restrictArmor",
         * false); boolean restrictWeapons = mySharedPreferences.getBoolean(
         * "restrictWeapons", false); boolean restrictPotions =
         * mySharedPreferences.getBoolean( "restrictPotions", false); boolean
         * restrictRings = mySharedPreferences.getBoolean("restrictRings",
         * false); boolean restrictRods =
         * mySharedPreferences.getBoolean("restrictRods", false); boolean
         * restrictScrolls = mySharedPreferences.getBoolean( "restrictScrolls",
         * false); boolean restrictStaves = mySharedPreferences.getBoolean(
         * "restrictStaves", false); boolean restrictWands =
         * mySharedPreferences.getBoolean("restrictWands", false); boolean
         * restrictWondrous = mySharedPreferences.getBoolean(
         * "restrictWondrous", false);
         * 
         * boolean displayValue = mySharedPreferences.getBoolean("displayValue",
         * true); boolean displayLootRoll = mySharedPreferences.getBoolean(
         * "displayLootRoll", false); boolean displayHoardValue =
         * mySharedPreferences.getBoolean( "displayHoardValue", false);
         */

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
