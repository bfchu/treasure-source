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

    public boolean saveEntry(String tableName, Integer id, int dRoll,
            int quantity, String itemName, Double gValue) {
        boolean successfull = false;
        if (id == null) {
            Log.d(TAG, "updating a new entry: d%: " + dRoll + ", quantity: "
                    + quantity + ", item Name: " + itemName + ", Value: "
                    + gValue);
            // create

            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dRoll", dRoll);
            newItem.put("quantity", quantity);
            newItem.put("itemName", itemName);
            newItem.put("value", gValue);

            long newId = db.insert(tableName, null, newItem);
            if (newId != -1) {
                successfull = true;
            }
        } else {
            Log.d(TAG, "updating a new entry: d%: " + dRoll + ", quantity: "
                    + quantity + ", item Name: " + itemName + ", Value: "
                    + gValue);
            // update

            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dRoll", dRoll);
            newItem.put("quantity", quantity);
            newItem.put("itemName", itemName);
            newItem.put("value", gValue);

            db.update(tableName, newItem, "id = " + id, null);

            successfull = true;

        }

        return successfull;
    }

    public boolean saveEntry(String tableName, Integer id, int dRoll,
            int quantity, String itemName, Double gValue, Boolean dispGold,
            Boolean dispRoll) {
        boolean successfull = false;
        int displayGold = 1;
        int displayRoll = 0;

        if (!dispGold) {
            displayGold = 0;
        }
        if (dispRoll) {
            displayRoll = 1;
        }

        if (id == null) {
            Log.d(TAG, "updating a new entry: d%: " + dRoll + ", quantity: "
                    + quantity + ", item Name: " + itemName + ", Value: "
                    + gValue);
            // create

            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dRoll", dRoll);
            newItem.put("quantity", quantity);
            newItem.put("itemName", itemName);
            newItem.put("value", gValue);
            newItem.put("dispGold", displayGold);
            newItem.put("dispRoll", displayRoll);

            long newId = db.insert(tableName, null, newItem);
            if (newId != -1) {
                successfull = true;
            }
        } else {
            Log.d(TAG, "updating a new entry: d%: " + dRoll + ", quantity: "
                    + quantity + ", item Name: " + itemName + ", Value: "
                    + gValue);
            // update

            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dRoll", dRoll);
            newItem.put("quantity", quantity);
            newItem.put("itemName", itemName);
            newItem.put("value", gValue);
            newItem.put("dispGold", displayGold);
            newItem.put("dispRoll", displayRoll);

            db.update(tableName, newItem, "id = " + id, null);

            successfull = true;

        }

        return successfull;
    }

    public boolean saveEntry(String tableName, Integer id, int dRoll,
            int quantity, String specials, String itemName, Double gValue,
            Boolean dispGold, Boolean dispRoll) {
        boolean successfull = false;
        int displayGold = 1;
        int displayRoll = 0;

        if (!dispGold) {
            displayGold = 0;
        }
        if (dispRoll) {
            displayRoll = 1;
        }
        if (id == null) {
            Log.d(TAG, "updating a new entry: d%: " + dRoll + ", quantity: "
                    + quantity + ", item Name: " + itemName + ", Value: "
                    + gValue);
            // create

            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dRoll", dRoll);
            newItem.put("quantity", quantity);
            newItem.put("specials", specials);
            newItem.put("itemName", itemName);
            newItem.put("value", gValue);
            newItem.put("dispGold", displayGold);
            newItem.put("dispRoll", displayRoll);

            long newId = db.insert(tableName, null, newItem);
            if (newId != -1) {
                successfull = true;
            }
        } else {
            Log.d(TAG, "updating a new entry: d%: " + dRoll + ", quantity: "
                    + quantity + ", item Name: " + itemName + ", Value: "
                    + gValue);
            // update

            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dRoll", dRoll);
            newItem.put("quantity", quantity);
            newItem.put("specials", specials);
            newItem.put("itemName", itemName);
            newItem.put("value", gValue);
            newItem.put("dispGold", displayGold);
            newItem.put("dispRoll", displayRoll);

            db.update(tableName, newItem, "id = " + id, null);

            successfull = true;

        }

        return successfull;
    }

    public void clear(String tableName) {
        db.delete(tableName, null, null);
    }

    @Deprecated
    public LootItem getTableItem(Integer dRoll, String tableName) {

        Cursor cursor = this.findItemByDRoll(dRoll, tableName);

        // Integer sanitizedId = cursor.getInt(0);
        String itemName = cursor.getString(3);
        double value = cursor.getDouble(4);

        LootItem item = new LootItem(dRoll, itemName, value);

        return item;
    }

    public Cursor findItemByDRoll(Integer dRoll, String tableName) {
        Cursor cursor = db.query(true, tableName, new String[] { "id", "dRoll",
                "quantity", "specials", "itemName", "value", "dispGold",
                "dispRoll" }, null, null, null, null, null, null);
        cursor.moveToFirst();

        int a = cursor.getInt(1);
        int b = cursor.getInt(2);

        while ((dRoll < a) || (dRoll > b)) {
            cursor.moveToNext();
            a = cursor.getInt(1);
            b = cursor.getInt(2);
        }

        return cursor;
    }

    public Cursor getLootOut() {
        return db.query("lootOut", new String[] { "id", "dRoll", "quantity",
                "specials", "itemName", "value", "dispGold", "dispRoll" },
                null, null, null, null, "id", null);
    }

    public Integer getNumDice(Integer dRoll, String tableName) {
        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "numDice", "dieSize", "quantity", "coinType" }, null,
                null, null, null, null, null);
        cursor.moveToFirst();

        int a = cursor.getInt(1);
        int b = cursor.getInt(2);
        int numDice = 1;

        while ((dRoll < a) || (dRoll > b)) {
            cursor.moveToNext();
            a = cursor.getInt(1);
            b = cursor.getInt(2);
            numDice = cursor.getInt(3);
        }

        return numDice;
    }

    public Integer getDieSize(Integer dRoll, String tableName) {
        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "numDice", "dieSize", "quantity", "coinType" }, null,
                null, null, null, null, null);
        cursor.moveToFirst();

        int a = cursor.getInt(1);
        int b = cursor.getInt(2);
        int dieSize = 1;

        while ((dRoll < a) || (dRoll > b)) {
            cursor.moveToNext();
            a = cursor.getInt(1);
            b = cursor.getInt(2);
            dieSize = cursor.getInt(4);
        }

        return dieSize;
    }

    public Integer getCoinType(Integer dRoll, String tableName) {
        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "numDice", "dieSize", "quantity", "coinType" }, null,
                null, null, null, null, null);
        cursor.moveToFirst();

        int a = cursor.getInt(1);
        int b = cursor.getInt(2);
        int coinType = 3;

        while ((dRoll < a) || (dRoll > b)) {
            cursor.moveToNext();
            a = cursor.getInt(1);
            b = cursor.getInt(2);
            coinType = cursor.getInt(6);
        }

        return coinType;
    }

    public Integer getGoodsType(Integer dRoll, String tableName) {
        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "numDice", "dieSize", "goodsType" }, null, null, null,
                null, null, null);
        cursor.moveToFirst();

        int a = cursor.getInt(1);
        int b = cursor.getInt(2);
        int goodsType = 1;

        while ((dRoll < a) || (dRoll > b)) {
            cursor.moveToNext();
            a = cursor.getInt(1);
            b = cursor.getInt(2);
            goodsType = cursor.getInt(5);
        }

        return goodsType;
    }

    public Integer getItemGroup(Integer dRoll, String tableName) {
        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "numDice", "dieSize", "itemRarityGroup" }, null, null,
                null, null, null, null);
        cursor.moveToFirst();

        int a = cursor.getInt(1);
        int b = cursor.getInt(2);
        int itemType = 1;

        while ((dRoll < a) || (dRoll > b)) {
            cursor.moveToNext();
            a = cursor.getInt(1);
            b = cursor.getInt(2);
            itemType = cursor.getInt(5);
        }

        return itemType;
    }

    public String getArmorType(Integer dRoll) {
        Cursor cursor = db.query(true, "ArmorTypes", new String[] { "id",
                "dLow", "dHigh", "armorType", "valueAdjust" }, null, null,
                null, null, null, null);
        cursor.moveToFirst();

        int a = cursor.getInt(1);
        int b = cursor.getInt(2);
        String armorType = "full plate";

        while ((dRoll < a) || (dRoll > b)) {
            cursor.moveToNext();
            a = cursor.getInt(1);
            b = cursor.getInt(2);
            armorType = cursor.getString(3);
        }

        return armorType;
    }

    public String getShieldType(Integer dRoll) {
        Cursor cursor = db.query(true, "ShieldTypes", new String[] { "id",
                "dLow", "dHigh", "shieldType", "valueAdjust" }, null, null,
                null, null, null, null);
        cursor.moveToFirst();

        int a = cursor.getInt(1);
        int b = cursor.getInt(2);
        String shieldType = "buckler";

        while ((dRoll < a) || (dRoll > b)) {
            cursor.moveToNext();
            a = cursor.getInt(1);
            b = cursor.getInt(2);
            shieldType = cursor.getString(3);
        }

        return shieldType;
    }

    public void getArmorSpecs(Integer dRoll, boolean isGreaterItem,
            Integer rarityLevel, Integer enhancement, Integer numAbilities,
            Integer abilityLevel) {
        // build table name
        String tableName = "Armor_";
        if (isGreaterItem) {
            tableName += "Greater_";
        } else {
            tableName += "Lesser_";
        }

        if (rarityLevel == 2) {
            tableName += "Minor";
        } else if (rarityLevel == 3) {
            tableName += "Medium";
        } else {
            tableName += "Major";
        }

        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "enhancement", "numAbilities", "abilityLevel" }, null,
                null, null, null, null, null);
        cursor.moveToFirst();

        int a = cursor.getInt(1);
        int b = cursor.getInt(2);

        while ((dRoll < a) || (dRoll > b)) {
            cursor.moveToNext();
            a = cursor.getInt(1);
            b = cursor.getInt(2);
            enhancement = cursor.getInt(3);
            numAbilities = cursor.getInt(4);
            abilityLevel = cursor.getInt(5);
        }

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
        private static final String CREATE_TABLE_LootOut = "CREATE TABLE lootOut "
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "dRoll int, "
                + "quantity int, "
                + "specials varchar(50), "
                + "itemName varchar(50), "
                + "value varchar(50), "
                + "dispGold int, " + "dispRoll int)";
        private static final String CREATE_TABLE_ArmorTypes = "CREATE TABLE ArmorTypes "
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "dLow int, "
                + "dHigh int, "
                + "armorType varchar(50), "
                + "valueAdjust int)";
        private static final String CREATE_TABLE_ShieldTypes = "CREATE TABLE ShieldTypes "
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "dLow int, "
                + "dHigh int, "
                + "shieldType varchar(50), "
                + "valueAdjust int)";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS contact";

        public LootDatabaseOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public LootDatabaseOpenHelper(Context context, String name,
                CursorFactory factory, int version) {
            super(context, name, factory, version);
            // TODO Auto-generated constructor stub
        }

        public void initTables(SQLiteDatabase db) {
            // Primary output
            db.execSQL(CREATE_TABLE_LootOut);
            // Armor Types
            db.execSQL(CREATE_TABLE_ArmorTypes);
            db.execSQL(CREATE_TABLE_ShieldTypes);

            // Coins by APL
            for (int ii = 0; ii < 20; ii++) {
                String sqlcmd = "CREATE TABLE APL" + (ii + 1) + "_Coins "
                        + "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "dLow int, " + "dHigh int, " + "numDice int, "
                        + "dieSize int, " + "quantity int, " + "coinType int)";
                db.execSQL(sqlcmd);
                Log.d(TAG, "Creating Table APL" + (ii + 1) + "_Coins");
            }

            // Goods by APL
            for (int ii = 0; ii < 20; ii++) {
                String sqlcmd = "CREATE TABLE APL" + (ii + 1) + "_Goods "
                        + "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "dLow int, " + "dHigh int, " + "numDice int, "
                        + "dieSize int, " + "goodsType int)";
                db.execSQL(sqlcmd);
                Log.d(TAG, "Creating Table APL" + (ii + 1) + "_Goods");
            }

            // Items by APL
            for (int ii = 0; ii < 20; ii++) {
                String sqlcmd = "CREATE TABLE APL" + (ii + 1) + "_Items "
                        + "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "dLow int, " + "dHigh int, " + "numDice int, "
                        + "dieSize int, " + "itemRarityGroup int)";
                db.execSQL(sqlcmd);
                Log.d(TAG, "Creating Table APL" + (ii + 1) + "_Items");
            }

            // Armor Enhancement and Abilities numbers by rarity
            for (int ii = 0; ii < 3; ii++) {
                for (int jj = 0; jj < 2; jj++) {
                    String lesserOrGreater = null;
                    switch (jj) {
                    case 0:
                        lesserOrGreater = "Lesser";
                        break;
                    case 1:
                        lesserOrGreater = "Greater";
                    }
                    String rarityLevel = null;
                    switch (ii) {
                    case 0:
                        rarityLevel = "Minor";
                        break;
                    case 1:
                        rarityLevel = "Medium";
                        break;
                    case 2:
                        rarityLevel = "Major";
                    }
                    String sqlcmd = "CREATE TABLE Armor_" + lesserOrGreater
                            + "_" + rarityLevel
                            + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "dLow int, " + "dHigh int, "
                            + "enhancement int, " + "numAbilities int, "
                            + "abilityLevel int)";

                    db.execSQL(sqlcmd);
                    Log.d(TAG, "Creating Table Armor_" + lesserOrGreater + "_"
                            + rarityLevel);
                }
            }
            // Special Abilities for Armor and Shield
            for (int ii = 0; ii < 5; ii++) {
                String sqlcmd = "CREATE TABLE Abilities_Armor_plus" + (ii + 1)
                        + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "dLow int, " + "dHigh int, "
                        + "ability varchar(50), " + "priceAdjust int)";

                db.execSQL(sqlcmd);
                Log.d(TAG, "Creating Table Abilities_Armor_" + "+" + (ii + 1));

            }
            // TODO: init table for shield abilities

            // Specific Magic Armor and shields
            for (int ii = 0; ii < 3; ii++) {
                for (int jj = 0; jj < 2; jj++) {
                    String lesserOrGreater = null;
                    switch (jj) {
                    case 0:
                        lesserOrGreater = "Lesser";
                        break;
                    case 1:
                        lesserOrGreater = "Greater";
                    }
                    String rarityLevel = null;
                    switch (ii) {
                    case 0:
                        rarityLevel = "Minor";
                        break;
                    case 1:
                        rarityLevel = "Medium";
                        break;
                    case 2:
                        rarityLevel = "Major";
                    }
                    String sqlcmd = "CREATE TABLE Specific_Armor_"
                            + lesserOrGreater + "_" + rarityLevel
                            + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "dLow int, " + "dHigh int, "
                            + "itemName varchar(50), " + "price int)";

                    db.execSQL(sqlcmd);
                    Log.d(TAG, "Creating Table Specific_Armor_"
                            + lesserOrGreater + "_" + rarityLevel);
                }
            }
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(TAG, "creating new database");
            initTables(db);

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