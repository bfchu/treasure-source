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
 * @author Brian
 * 
 */
public class LootDisplay extends Activity {

    private static final String TAG = "LootDisplay";
    private ListView lootListView;
    private ArrayList<LootListTestItem> lootItemList;
    private ArrayAdapter<LootListTestItem> arrayAdapter;
    private LootDB lootDB;
    private Cursor lootCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loot_display);

        lootListView = (ListView) findViewById(R.id.lootListView);
        lootItemList = new ArrayList<LootListTestItem>();

        arrayAdapter = new ArrayAdapter<LootListTestItem>(this,
                android.R.layout.simple_list_item_1, lootItemList);
        Log.d(TAG, "lootListView: " + lootListView);
        lootListView.setAdapter(arrayAdapter);

        // Database code
        lootDB = new LootDB(this);

        // create the database
        lootDB.open();

        populateLootDB();
    }

    private void populateLootDB() {
        // get all the Entries
        lootCursor = lootDB.getAllEntries();
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
                int dLow = lootCursor.getInt(1);
                int dHigh = lootCursor.getInt(2);
                String itemName = lootCursor.getString(3);
                double value = lootCursor.getDouble(4);

                Log.d(TAG, "");

                LootListTestItem item = new LootListTestItem(id, dLow, dHigh,
                        itemName, value);
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
