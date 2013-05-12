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

    public boolean saveEntry(String tableName, Integer id, LootOutListItem item) {
        boolean successfull = false;
        int displayGold = 1;
        int displayRoll = 0;

        if (!item.isDispGold()) {
            displayGold = 0;
        }
        if (item.isDispRoll()) {
            displayRoll = 1;
        }
        if (id == null) {
            Log.d(TAG, "Creating a new entry: d%: " + item.getNumRolled()
                    + ", quantity: " + item.getQuantity() + ", item Name: "
                    + item.getName() + ", Value: " + item.getgValue());
            // create

            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dRoll", item.getNumRolled());
            newItem.put("quantity", item.getQuantity());
            newItem.put("specials", item.getSpecials());
            newItem.put("itemName", item.getName());
            newItem.put("value", item.getgValue());
            newItem.put("dispGold", displayGold);
            newItem.put("dispRoll", displayRoll);

            long newId = db.insert(tableName, null, newItem);
            if (newId != -1) {
                successfull = true;
            }
        } else {
            Log.d(TAG, "updating am entry: d%: " + item.getNumRolled()
                    + ", quantity: " + item.getQuantity() + ", item Name: "
                    + item.getName() + ", Value: " + item.getgValue());
            // update

            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dRoll", item.getNumRolled());
            newItem.put("quantity", item.getQuantity());
            newItem.put("specials", item.getSpecials());
            newItem.put("itemName", item.getName());
            newItem.put("value", item.getgValue());
            newItem.put("dispGold", displayGold);
            newItem.put("dispRoll", displayRoll);

            db.update(tableName, newItem, "id = " + id, null);

            successfull = true;

        }

        return successfull;
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
                "dLow", "dHigh", "ArmorType", "valueAdjust" }, null, null,
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
                "dLow", "dHigh", "ShieldType", "valueAdjust" }, null, null,
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

    public String getMeleeWeaponType(Integer dRoll) {
        Cursor cursor = db.query(true, "WeaponTypes", new String[] { "id",
                "dLow", "dHigh", "WeaponType", "valueAdjust" }, null, null,
                null, null, null, null);
        cursor.moveToFirst();

        int a = cursor.getInt(1);
        int b = cursor.getInt(2);
        String weaponType = "longsword";

        while ((dRoll < a) || (dRoll > b)) {
            cursor.moveToNext();
            a = cursor.getInt(1);
            b = cursor.getInt(2);
            weaponType = cursor.getString(3);
        }

        return weaponType;
    }

    public String getRangedWeaponType(Integer dRoll) {
        Cursor cursor = db.query(true, "RangedWeaponTypes", new String[] {
                "id", "dLow", "dHigh", "RangedWeaponType", "valueAdjust" },
                null, null, null, null, null, null);
        cursor.moveToFirst();

        int a = cursor.getInt(1);
        int b = cursor.getInt(2);
        String weaponType = "shortbow";

        while ((dRoll < a) || (dRoll > b)) {
            cursor.moveToNext();
            a = cursor.getInt(1);
            b = cursor.getInt(2);
            weaponType = cursor.getString(3);
        }

        return weaponType;
    }

    public String getAmmoType(Integer dRoll) {
        Cursor cursor = db.query(true, "AmmoWeaponTypes", new String[] { "id",
                "dLow", "dHigh", "AmmoType", "valueAdjust" }, null, null, null,
                null, null, null);
        cursor.moveToFirst();

        int a = cursor.getInt(1);
        int b = cursor.getInt(2);
        String ammoType = "dart";

        while ((dRoll < a) || (dRoll > b)) {
            cursor.moveToNext();
            a = cursor.getInt(1);
            b = cursor.getInt(2);
            ammoType = cursor.getString(3);
        }

        return ammoType;
    }

    public String getWondrousType(Integer dRoll) {
        Cursor cursor = db.query(true, "WondrousItemTypes", new String[] {
                "id", "dLow", "dHigh", "itemType", "valueAdjust" }, null, null,
                null, null, null, null);
        cursor.moveToFirst();

        int a = cursor.getInt(1);
        int b = cursor.getInt(2);
        String itemType = "Belt";

        while ((dRoll < a) || (dRoll > b)) {
            cursor.moveToNext();
            a = cursor.getInt(1);
            b = cursor.getInt(2);
            itemType = cursor.getString(3);
        }

        return itemType;
    }

    public void getArmorSpecs(Integer dRoll, boolean isGreaterItem,
            Integer rarityLevel, Integer enhancement, Integer numAbilities,
            Integer abilityLevel, Integer isSpecific) {
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
                "dHigh", "enhancement", "numAbilities", "abilityLevel",
                "isSpecific" }, null, null, null, null, null, null);
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
            isSpecific = cursor.getInt(6);
        }

    }

    public void getWeaponSpecs(Integer dRoll, boolean isGreaterItem,
            Integer rarityLevel, Integer enhancement, Integer numAbilities,
            Integer abilityLevel, Integer isSpecific) {
        // build table name
        String tableName = "Weapon_";
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
                "dHigh", "enhancement", "numAbilities", "abilityLevel",
                "isSpecific" }, null, null, null, null, null, null);
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
            isSpecific = cursor.getInt(6);
        }

    }

    public void getSpecificItem(Integer dRoll, String itemType,
            boolean isGreaterItem, Integer rarityLevel, String name,
            double gValue) {
        // build table name
        String tableName = itemType + "_";
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
                "dHigh", "itemName", "price" }, null, null, null, null, null,
                null);
        cursor.moveToFirst();

        int a = cursor.getInt(1);
        int b = cursor.getInt(2);

        while ((dRoll < a) || (dRoll > b)) {
            cursor.moveToNext();
            a = cursor.getInt(1);
            b = cursor.getInt(2);
            name = cursor.getString(3);
            gValue = cursor.getDouble(4);
        }

    }

    public String getAbilities(Integer dRoll, String itemType,
            Integer abilityLevel, double priceToAdjust) {
        // build table name
        String tableName = "Abilities_" + itemType + "+" + abilityLevel;

        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "ability", "priceAdjust" }, null, null, null, null,
                null, null);
        cursor.moveToFirst();

        int a = cursor.getInt(1);
        int b = cursor.getInt(2);
        String ability = null;
        double priceAdjust = 0.0;

        while ((dRoll < a) || (dRoll > b)) {
            cursor.moveToNext();
            a = cursor.getInt(1);
            b = cursor.getInt(2);
            ability = cursor.getString(3);
            priceAdjust = cursor.getDouble(4);
        }

        priceToAdjust = priceAdjust;

        return ability;
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

            // Item Types
            initItemTypeTable(db, "Armor");
            initItemTypeTable(db, "Shield");
            initItemTypeTable(db, "Weapon");
            initItemTypeTable(db, "RangedWeapon");
            initItemTypeTable(db, "Ammo");
            initItemTypeTable(db, "WondrousItem");

            // Armor Enhancement and Abilities numbers by rarity
            initEnhancementTable(db, "Armor");
            initEnhancementTable(db, "Weapons");

            // Special Abilities for Armor, Shield, Weapons:
            initAbilitiesTable(db, "Armor");
            initAbilitiesTable(db, "Shields");
            initAbilitiesTable(db, "Weapons");
            initAbilitiesTable(db, "Ranged_Weapons");
            initAbilitiesTable(db, "Ammunition");

            // Specific items
            initSpecificItemTable(db, "Armor");
            initSpecificItemTable(db, "Shields");
            initSpecificItemTable(db, "Weapons");
            initSpecificItemTable(db, "Potions");
            initSpecificItemTable(db, "Rings");
            initSpecificItemTable(db, "Rods");
            initSpecificItemTable(db, "Staves");

            initSpecificItemTable(db, "Wondrous_Belt");
            initSpecificItemTable(db, "Wondrous_Body");
            initSpecificItemTable(db, "Wondrous_Chest");
            initSpecificItemTable(db, "Wondrous_Eyes");
            initSpecificItemTable(db, "Wondrous_Feet");
            initSpecificItemTable(db, "Wondrous_Hands");
            initSpecificItemTable(db, "Wondrous_Head");
            initSpecificItemTable(db, "Wondrous_Headband");
            initSpecificItemTable(db, "Wondrous_Neck");
            initSpecificItemTable(db, "Wondrous_Shoulders");
            initSpecificItemTable(db, "Wondrous_Wrists");
            initSpecificItemTable(db, "Wondrous_Slotless");

        }

        public void initItemTypeTable(SQLiteDatabase db, String itemType) {
            String sqlcmd = "CREATE TABLE " + itemType + "Types "
                    + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " + "dLow int, "
                    + "dHigh int, " + itemType + "Type varchar(50), "
                    + "valueAdjust int)";

            db.execSQL(sqlcmd);
            Log.d(TAG, "Creating Table " + itemType + "Types ");
        }

        public void initEnhancementTable(SQLiteDatabase db, String itemType) {
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
                    String sqlcmd = "CREATE TABLE " + itemType + "_"
                            + lesserOrGreater + "_" + rarityLevel
                            + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "dLow int, " + "dHigh int, "
                            + "enhancement int, " + "numAbilities int, "
                            + "abilityLevel int, " + "isSpecific int)";

                    db.execSQL(sqlcmd);
                    Log.d(TAG, "Creating " + itemType + "_" + lesserOrGreater
                            + "_" + rarityLevel);
                }
            }
        }

        public void initAbilitiesTable(SQLiteDatabase db, String itemType) {
            for (int ii = 0; ii < 5; ii++) {
                String sqlcmd = "CREATE TABLE Abilities_" + itemType + "_plus"
                        + (ii + 1) + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "dLow int, " + "dHigh int, "
                        + "ability varchar(50), " + "priceAdjust int)";

                db.execSQL(sqlcmd);
                Log.d(TAG, "Creating Table Abilities_" + itemType + "+"
                        + (ii + 1));

            }
        }

        public void initSpecificItemTable(SQLiteDatabase db, String itemType) {
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
                    String sqlcmd = "CREATE TABLE Specific_" + itemType + "_"
                            + lesserOrGreater + "_" + rarityLevel
                            + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "dLow int, " + "dHigh int, "
                            + "itemName varchar(50), " + "price int)";

                    db.execSQL(sqlcmd);
                    Log.d(TAG, "Creating Table Specific_" + itemType + "_"
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