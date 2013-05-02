/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 */
public class LootDB {

    private static final String TAG = "LootDB";

    /** name of the database file on the file system */
    private static final String DATABASE_NAME = "TreasureSource.db";
    /** version of the database */
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;
    private Context context;
    private LootDatabaseOpenHelper dbHelper;

    public LootDB(Context context) {
        this.context = context;
        dbHelper = new LootDatabaseOpenHelper(context);
    }

    public void open() throws SQLiteException {
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            // if opening for writing fails, try to open for reading only
            db = dbHelper.getReadableDatabase();
        }
    }

    public void close() {
        db.close();
    }

    public boolean saveEntry(Integer id, String dLow, int dHigh, int itemName,
            Double gValue) {
        boolean successfull = false;
        if (id == null) {
            Log.d(TAG, "creating a new entry: d%: " + dLow + " - " + dHigh
                    + " item Name: " + itemName + " Value: " + gValue);
            // create

            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dLow", dLow);
            newItem.put("dHigh", dHigh);
            newItem.put("itemName", itemName);
            newItem.put("value", gValue);

            long newId = db.insert("lootTest", null, newItem);
            if (newId != -1) {
                successfull = true;
            }
        } else {
            Log.d(TAG, "updating a new entry: d%: " + dLow + " - " + dHigh
                    + " item Name: " + itemName + " Value: " + gValue);
            // update

            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dLow", dLow);
            newItem.put("dHigh", dHigh);
            newItem.put("itemName", itemName);
            newItem.put("value", gValue);

            db.update("lootTest", newItem, "id = " + id, null);

            successfull = true;

        }

        return successfull;
    }

    public LootItem getItem(Integer dRoll) {

        Cursor cursor = this.findItemByDRoll(dRoll);

        Integer sanitizedId = cursor.getInt(0);

        String itemName = cursor.getString(3);
        double value = cursor.getDouble(4);

        LootItem item = new LootItem(dRoll, itemName, value);

        return item;
    }

    public Cursor getAllEntries() {
        return db
                .query("lootTest", new String[] { "id", "dLow", "dHigh",
                        "itemName", "value" }, "id = 0", null, null, null,
                        "dLow", null);
    }

    public Cursor findItemByDRoll(Integer dRoll) {
        Cursor cursor = db.query(true, "lootTest", new String[] { "id", "dLow",
                "dHigh", "itemName", "value" }, "id = 0", null, null, null,
                null, null);

        int a = cursor.getInt(1);
        int b = cursor.getInt(2);

        while ((dRoll < a) || (dRoll > b)) {
            cursor.moveToNext();
            a = cursor.getInt(1);
            b = cursor.getInt(2);
        }

        return cursor;
    }

    /*
     * query( boolean distinct - true or false - true if you want each row to be
     * unique, false otherwise. String table - The table name to compile the
     * query against. String[] columns - and array of column names to return
     * String selection - the where clause String[] selectionArgs - arguments to
     * the where clause - advanced topic, just use null - see
     * findContactsByPhoneNumber below String groupBy - advanced topic, use null
     * String having - advanced topic, use null String orderBy - sort the
     * results - null for however the database feels like returning the data
     * String limit - limit the number of row to return )
     */
    /*
     * public Something get<something>(Integer id) { }
     */

    /**
     * 
     * @author Brian Chu and Garrick Ablett
     * 
     */
    private static class LootDatabaseOpenHelper extends SQLiteOpenHelper {
        private static final String TAG = "LootDatabaseOpenHelper";
        private static final String CREATE_TABLE = "CREATE TABLE lootTest "
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " + "dLow int, "
                + "dHigh int, " + "itemName varchar(50), "
                + "value varchar(50))";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS contact";

        public LootDatabaseOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public LootDatabaseOpenHelper(Context context, String name,
                CursorFactory factory, int version) {
            super(context, name, factory, version);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(TAG, "creating new database");
            db.execSQL(CREATE_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data.");
            db.execSQL(DROP_TABLE);

            // recreate the table(s)
            onCreate(db);

        }

    }
}