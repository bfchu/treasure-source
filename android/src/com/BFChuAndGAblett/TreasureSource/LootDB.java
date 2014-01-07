/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
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
    private static String DATABASE_PATH = "";

    private SQLiteDatabase db;
    private Context context;
    private LootDatabaseOpenHelper dbHelper;

    public LootDB(Context context) throws IOException {
        this.context = context;
        dbHelper = new LootDatabaseOpenHelper(context);

    }

    public void open() throws SQLiteException {
        try {
			dbHelper.copyDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            Log.d(TAG, "In LootDB.open(): failed to get writable Databade, getting readable Database instead.");
            db = dbHelper.getReadableDatabase();
        }
    }

    public void close() {
        db.close();
    }

    public void clear(String tableName) {
        db.delete(tableName, null, null);
    }

   
    
    /**
     * DATABASE ENTRY SAVERS
     * 
     * */
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

    public boolean saveEntryEncounterVals(String tableName, Integer id,
            int APL, double slowGold, double mediumGold, double fastGold) {
        boolean successfull = false;
        if (id == null) {

            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("APL", APL);
            newItem.put("slowGold", slowGold);
            newItem.put("mediumGold", mediumGold);
            newItem.put("fastGold", fastGold);

            long newId = db.insert(tableName, null, newItem);
            if (newId != -1) {
                successfull = true;
            }
        } else {
            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("APL", APL);
            newItem.put("slowGold", slowGold);
            newItem.put("mediumGold", mediumGold);
            newItem.put("fastGold", fastGold);

            db.update(tableName, newItem, "id = " + id, null);

            successfull = true;

        }

        return successfull;
    }

    public boolean saveEntryItemTypes(String tableName, Integer id, int dLow,
            int dHigh, String itemName, Double valueAdjust) {
        boolean successfull = false;
        if (id == null) {
            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dLow", dLow);
            newItem.put("dHigh", dHigh);
            newItem.put("itemType", itemName);
            newItem.put("valueAdjust", valueAdjust);

            long newId = db.insert(tableName, null, newItem);
            if (newId != -1) {
                successfull = true;
            }
        } else {
            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dLow", dLow);
            newItem.put("dHigh", dHigh);
            newItem.put("itemType", itemName);
            newItem.put("valueAdjust", valueAdjust);

            db.update(tableName, newItem, "id = " + id, null);

            successfull = true;

        }

        return successfull;
    }

    private boolean saveEntryMagicVals(String tableName, Integer id,
            Integer magicLevel, Double valueAdjust) {
        boolean successfull = false;
        if (id == null) {
            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("magicLevel", magicLevel);
            newItem.put("valueAdjust", valueAdjust);

            long newId = db.insert(tableName, null, newItem);
            if (newId != -1) {
                successfull = true;
            }
        } else {
            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("magicLevel", magicLevel);
            newItem.put("valueAdjust", valueAdjust);

            db.update(tableName, newItem, "id = " + id, null);

            successfull = true;
        }
        return successfull;
    }

    private void saveEntryEnhancement(String tableName, Integer id,
            Integer dLow, Integer dHigh, Integer enhancement,
            Integer numAbilities, Integer abilityLevel, Integer isSpecific) {
        boolean successfull = false;
        if (id == null) {
            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dLow", dLow);
            newItem.put("dHigh", dHigh);
            newItem.put("enhancement", enhancement);
            newItem.put("numAbilities", numAbilities);
            newItem.put("abilityLevel", abilityLevel);
            newItem.put("isSpecific", isSpecific);

            long newId = db.insert(tableName, null, newItem);
            if (newId != -1) {
                successfull = true;
            }
        } else {
            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dLow", dLow);
            newItem.put("dHigh", dHigh);
            newItem.put("enhancement", enhancement);
            newItem.put("numAbilities", numAbilities);
            newItem.put("abilityLevel", abilityLevel);
            newItem.put("isSpecific", isSpecific);

            db.update(tableName, newItem, "id = " + id, null);

            successfull = true;
        }
    }

    private boolean saveEntryAbilities(String tableName, Integer id,
            Integer dLow, Integer dHigh, String ability, Double priceAdjust) {
        boolean successfull = false;
        if (id == null) {
            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dLow", dLow);
            newItem.put("dHigh", dHigh);
            newItem.put("ability", ability);
            newItem.put("priceAdjust", priceAdjust);

            long newId = db.insert(tableName, null, newItem);
            if (newId != -1) {
                successfull = true;
            }
        } else {
            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dLow", dLow);
            newItem.put("dHigh", dHigh);
            newItem.put("ability", ability);
            newItem.put("priceAdjust", priceAdjust);

            db.update(tableName, newItem, "id = " + id, null);

            successfull = true;

        }

        return successfull;

    }

    private boolean saveEntrySpecificItems(String tableName, Integer id,
            Integer dLow, Integer dHigh, String itemName, Double price) {
        boolean successfull = false;
        if (id == null) {
            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dLow", dLow);
            newItem.put("dHigh", dHigh);
            newItem.put("itemName", itemName);
            newItem.put("price", price);

            long newId = db.insert(tableName, null, newItem);
            if (newId != -1) {
                successfull = true;
            }
        } else {
            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dLow", dLow);
            newItem.put("dHigh", dHigh);
            newItem.put("itemName", itemName);
            newItem.put("price", price);

            db.update(tableName, newItem, "id = " + id, null);

            successfull = true;

        }

        return successfull;

    }

    private boolean saveEntryAPLcoins(String tableName, Integer id,
            Integer dLow, Integer dHigh, Integer numDice, Integer dieSize,
            Integer quantity, Integer coinType) {
        boolean successfull = false;
        if (id == null) {

            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dLow", dLow);
            newItem.put("dHigh", dHigh);
            newItem.put("numDice", numDice);
            newItem.put("dieSize", dieSize);
            newItem.put("quantity", quantity);
            newItem.put("coinType", coinType);

            long newId = db.insert(tableName, null, newItem);
            if (newId != -1) {
                successfull = true;
            }
        } else {

            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dLow", dLow);
            newItem.put("dHigh", dHigh);
            newItem.put("numDice", numDice);
            newItem.put("dieSize", dieSize);
            newItem.put("quantity", quantity);
            newItem.put("coinType", coinType);

            db.update(tableName, newItem, "id = " + id, null);

            successfull = true;

        }

        return successfull;

    }

    private boolean saveEntryAPLGoods(String tableName, Integer id,
            Integer dLow, Integer dHigh, Integer numDice, Integer dieSize,
            Integer goodsType) {
        boolean successfull = false;
        if (id == null) {
            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dLow", dLow);
            newItem.put("dHigh", dHigh);
            newItem.put("numDice", numDice);
            newItem.put("dieSize", dieSize);
            newItem.put("goodsType", goodsType);

            long newId = db.insert(tableName, null, newItem);
            if (newId != -1) {
                successfull = true;
            }
        } else {

            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dLow", dLow);
            newItem.put("dHigh", dHigh);
            newItem.put("numDice", numDice);
            newItem.put("dieSize", dieSize);
            newItem.put("goodsType", goodsType);

            db.update(tableName, newItem, "id = " + id, null);

            successfull = true;

        }

        return successfull;

    }

    private boolean saveEntryAPLItems(String tableName, Integer id,
            Integer dLow, Integer dHigh, Integer numDice, Integer dieSize,
            Integer itemRarityGroup) {
        boolean successfull = false;
        if (id == null) {

            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dLow", dLow);
            newItem.put("dHigh", dHigh);
            newItem.put("numDice", numDice);
            newItem.put("dieSize", dieSize);
            newItem.put("itemRarityGroup", itemRarityGroup);

            long newId = db.insert(tableName, null, newItem);
            if (newId != -1) {
                successfull = true;
            }
        } else {

            // Create a new row:
            ContentValues newItem = new ContentValues();
            // Assign values for each column.
            newItem.put("dLow", dLow);
            newItem.put("dHigh", dHigh);
            newItem.put("numDice", numDice);
            newItem.put("dieSize", dieSize);
            newItem.put("itemRarityGroup", itemRarityGroup);

            db.update(tableName, newItem, "id = " + id, null);

            successfull = true;

        }

        return successfull;

    }

    // TODO: remaining saveEntry forms;

    /**
     * DATABASE CALLERS
     * */

    private void moveCursorByDRoll(Cursor cursor, Integer dRoll) {

        cursor.moveToFirst();
        int a = cursor.getInt(1);
        int b = cursor.getInt(2);
        // is dRoll between a and b?s
        while ((dRoll < a) || (dRoll > b)) {
            cursor.moveToNext();
            a = cursor.getInt(1);
            b = cursor.getInt(2);
        }
    }

    public Cursor findItemByDRoll(Integer dRoll, String tableName) {
        Cursor cursor = db.query(true, tableName, new String[] { "id", "dRoll",
                "quantity", "specials", "itemName", "value", "dispGold",
                "dispRoll" }, null, null, null, null, null, null);
        cursor.moveToFirst();
        moveCursorByDRoll(cursor, dRoll);

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
        int numDice = cursor.getInt(3);
        moveCursorByDRoll(cursor, dRoll);
        numDice = cursor.getInt(3);
        return numDice;
    }

    public Integer getDieSize(Integer dRoll, String tableName) {
        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "numDice", "dieSize", "quantity", "coinType" }, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        int dieSize = cursor.getInt(4);
        moveCursorByDRoll(cursor, dRoll);
        dieSize = cursor.getInt(4);
        return dieSize;
    }

    public Double getEncounterValue(Integer APL, Integer campaignSpeed) {
        Cursor cursor = db.query(true, "Encounter_Values", new String[] { "id",
                "APL", "slowGold", "mediumGold", "fastGold" }, null, null,
                null, null, null, null);
        cursor.moveToFirst();

        int a = cursor.getInt(1);
        Integer campSpeedIndex = campaignSpeed + 1;
        double gold = cursor.getDouble(campSpeedIndex);

        while (a != APL) {
            cursor.moveToNext();
            a = cursor.getInt(1);
        }
        gold = cursor.getDouble(campSpeedIndex);
        return gold;
    }

    public Cursor getCoinsByAPL(String tableName, Integer dRoll) {
        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "numDice", "dieSize", "quantity", "coinType" }, null,
                null, null, null, null, null);

        moveCursorByDRoll(cursor, dRoll);
        return cursor;
    }

    public Cursor getGoodsByAPL(String tableName, Integer dRoll) {
        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "numDice", "dieSize", "goodsType" }, null, null, null,
                null, null, null);
        cursor.moveToFirst();
        moveCursorByDRoll(cursor, dRoll);
        return cursor;
    }

    public Cursor getItemsByAPL(String tableName, Integer dRoll) {
        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "numDice", "dieSize", "itemRarityGroup" }, null, null,
                null, null, null, null);
        cursor.moveToFirst();

        moveCursorByDRoll(cursor, dRoll);

        return cursor;
    }

    // TODO: consolodate item type methods into one and implement in lootCalc
    public String getItemType(String tableName, Integer dRoll) {
        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "itemType", "valueAdjust" }, null, null, null, null,
                null, null);
        cursor.moveToFirst();

        String itemType = cursor.getString(3);
        moveCursorByDRoll(cursor, dRoll);
        itemType = cursor.getString(3);

        return itemType;
    }

    public String getMundaneType(Integer dRoll) {
        Cursor cursor = db.query(true, "MundaneTypes", new String[] { "id",
                "dLow", "dHigh", "itemType", "valueAdjust" }, null, null, null,
                null, null, null);
        cursor.moveToFirst();

        String itemType = cursor.getString(3);
        moveCursorByDRoll(cursor, dRoll);
        itemType = cursor.getString(3);

        return itemType;
    }

    public String getArmorType(Integer dRoll) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in getArmorType(): dRoll" + dRoll);
        }
        Cursor cursor = db.query(true, "ArmorTypes", new String[] { "id",
                "dLow", "dHigh", "itemType", "valueAdjust" }, null, null, null,
                null, null, null);
        cursor.moveToFirst();

        String armorType = cursor.getString(3);
        moveCursorByDRoll(cursor, dRoll);
        armorType = cursor.getString(3);

        return armorType;
    }

    public double getItemValue(String tableName, Integer dRoll) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "in getArmorValue(): dRoll" + dRoll);
        }
        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "itemType", "valueAdjust" }, null, null, null, null,
                null, null);

        cursor.moveToFirst();

        double gValue = cursor.getDouble(4);
        moveCursorByDRoll(cursor, dRoll);
        gValue = cursor.getDouble(4);

        return gValue;
    }

    public double getMagicPrice(Integer mLevel, String itemType) {
        String tableName = "magicAdjustValues_" + itemType;
        Cursor cursor = db.query(true, tableName, new String[] { "id",
                "magicLevel", "valueAdjust" }, null, null, null, null, null,
                null);
        cursor.moveToFirst();
        int a = cursor.getInt(1);
        double price = cursor.getDouble(2);

        while (a != mLevel) {
            cursor.moveToNext();
            a = cursor.getInt(1);
        }
        price = cursor.getDouble(2);
        return price;
    }

    public String getShieldType(Integer dRoll) {
        Cursor cursor = db.query(true, "ShieldTypes", new String[] { "id",
                "dLow", "dHigh", "itemType", "valueAdjust" }, null, null, null,
                null, null, null);
        cursor.moveToFirst();

        String shieldType = cursor.getString(3);
        moveCursorByDRoll(cursor, dRoll);
        shieldType = cursor.getString(3);
        return shieldType;
    }

    public String getMeleeWeaponType(Integer dRoll) {
        Cursor cursor = db.query(true, "WeaponTypes", new String[] { "id",
                "dLow", "dHigh", "itemType", "valueAdjust" }, null, null, null,
                null, null, null);
        cursor.moveToFirst();

        String weaponType = cursor.getString(3);
        moveCursorByDRoll(cursor, dRoll);
        weaponType = cursor.getString(3);

        return weaponType;
    }

    public String getRangedWeaponType(Integer dRoll) {
        Cursor cursor = db.query(true, "RangedWeaponTypes", new String[] {
                "id", "dLow", "dHigh", "itemType", "valueAdjust" }, null, null,
                null, null, null, null);
        cursor.moveToFirst();

        String weaponType = cursor.getString(3);
        moveCursorByDRoll(cursor, dRoll);
        weaponType = cursor.getString(3);
        return weaponType;
    }

    public String getAmmoType(Integer dRoll) {
        Cursor cursor = db.query(true, "AmmoTypes", new String[] { "id",
                "dLow", "dHigh", "itemType", "valueAdjust" }, null, null, null,
                null, null, null);
        cursor.moveToFirst();

        String ammoType = cursor.getString(3);
        moveCursorByDRoll(cursor, dRoll);
        ammoType = cursor.getString(3);
        return ammoType;
    }

    public String getWondrousType(Integer dRoll) {
        Cursor cursor = db.query(true, "WondrousItemTypes", new String[] {
                "id", "dLow", "dHigh", "itemType", "valueAdjust" }, null, null,
                null, null, null, null);
        cursor.moveToFirst();

        String itemType = cursor.getString(3);

        moveCursorByDRoll(cursor, dRoll);
        itemType = cursor.getString(3);
        return itemType;
    }

    public Cursor getEnhancement(Integer dRoll, String itemType,
            boolean isGreaterItem, Integer rarityLevel) {
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
                "dHigh", "enhancement", "numAbilities", "abilityLevel",
                "isSpecific" }, null, null, null, null, null, null);
        cursor.moveToFirst();

        moveCursorByDRoll(cursor, dRoll);

        return cursor;
    }

    public LootItem getMundaneWeapon(Integer dRoll) {

        String tableName = "WeaponTypes";
        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "itemType", "valueAdjust" }, null, null, null, null,
                null, null);
        cursor.moveToFirst();

        String itemName = cursor.getString(3);
        Double gValue = cursor.getDouble(4);
        moveCursorByDRoll(cursor, dRoll);
        itemName = cursor.getString(3);
        gValue = cursor.getDouble(4);

        LootItem item = new LootItem();
        item.setName("Masterwork " + itemName);
        item.setgValue(gValue);
        item.setItemType(4);

        return item;
    }

    public LootItem getMundaneAlchemical(Integer dRoll) {

        String tableName = "Mundane_Alchemical_item";
        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "itemName", "price" }, null, null, null, null, null,
                null);
        cursor.moveToFirst();

        String itemName = cursor.getString(3);
        Double gValue = cursor.getDouble(4);
        moveCursorByDRoll(cursor, dRoll);

        itemName = cursor.getString(3);
        gValue = cursor.getDouble(4);
        LootItem item = new LootItem();
        item.setName(itemName);
        item.setgValue(gValue);
        item.setItemType(0);

        return item;
    }

    public LootItem getMundaneArmor(Integer dRoll) {

        String tableName = "Mundane_Armor";
        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "itemName", "price" }, null, null, null, null, null,
                null);
        cursor.moveToFirst();

        String itemName = cursor.getString(3);
        Double gValue = cursor.getDouble(4);

        moveCursorByDRoll(cursor, dRoll);

        itemName = cursor.getString(3);
        gValue = cursor.getDouble(4);
        LootItem item = new LootItem();
        item.setName(itemName);
        item.setgValue(gValue);
        item.setItemType(3);

        return item;
    }

    public LootItem getMundaneToolsGear(Integer dRoll) {

        String tableName = "Mundane_Tools_and_gear";
        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "itemName", "price" }, null, null, null, null, null,
                null);
        cursor.moveToFirst();

        String itemName = cursor.getString(3);
        Double gValue = cursor.getDouble(4);
        moveCursorByDRoll(cursor, dRoll);

        itemName = cursor.getString(3);
        gValue = cursor.getDouble(4);

        LootItem item = new LootItem();
        item.setName(itemName);
        item.setgValue(gValue);
        item.setItemType(4);

        return item;
    }

    public LootItem getSpecificItem(Integer dRoll, String itemType,
            boolean isGreaterItem, Integer rarityLevel) {
        // build table name
        String tableName = "Specific_" + itemType + "_";
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

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Pulling Specific item: " + dRoll + " from " + tableName);
        }

        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "itemName", "price" }, null, null, null, null, null,
                null);
        cursor.moveToFirst();

        LootItem item = new LootItem();
        item.setNumRolled(dRoll);
        String itemName = cursor.getString(3);
        Double price = cursor.getDouble(4);

        moveCursorByDRoll(cursor, dRoll);

        itemName = cursor.getString(3);
        price = cursor.getDouble(4);
        item.setName(itemName);
        item.setgValue(price);

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Found Specific item: " + dRoll + " from " + tableName
                    + ": " + item.getName());
        }

        return item;
    }

    public Cursor getAbilities(Integer dRoll, String itemType,
            Integer abilityLevel) {
        // build table name
        String tableName = "Abilities_" + itemType + "_plus" + abilityLevel;

        Cursor cursor = db.query(true, tableName, new String[] { "id", "dLow",
                "dHigh", "ability", "priceAdjust" }, null, null, null, null,
                null, null);
        cursor.moveToFirst();

        moveCursorByDRoll(cursor, dRoll);

        return cursor;
    }


    // TODO: remaining popTable forms;

    public String[] getLine(LootIO table) throws IOException {
        String[] line = table.getIn().readLine().split("\t");
        if (line.length <= 1) {
            line = table.getIn().readLine().split("\t");
        }

        return line;
    }

    /**
     * LootDatabaseOpenHelper
     * 
     * @author Brian Chu and Garrick Ablett
     * 
     * 
     * 
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
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS lootOut";
        private Context mContext;

        public LootDatabaseOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            if(android.os.Build.VERSION.SDK_INT >= 17){
                DATABASE_PATH = context.getApplicationInfo().dataDir + "/databases/";         
            }	else {
                DATABASE_PATH = "/data/data/" + context.getPackageName() + "/databases/";
            }
            this.mContext = context;
        }

        public LootDatabaseOpenHelper(Context context, String name,
                CursorFactory factory, int version) {
            super(context, name, factory, version);
            if(android.os.Build.VERSION.SDK_INT >= 17){
                DATABASE_PATH = context.getApplicationInfo().dataDir + "/databases/";         
            }	else {
                DATABASE_PATH = "/data/data/" + context.getPackageName() + "/databases/";
            }
            this.mContext = context;
        }

        private void clearLootOutTable(SQLiteDatabase db) {
            db.execSQL("DROP TABLE IF EXISTS lootOut");
            db.execSQL(CREATE_TABLE_LootOut);
        }

        //Copy the database from assets
        private void copyDataBase() throws IOException
        {
            InputStream mInput = mContext.getAssets().open(DATABASE_NAME);
            String outFileName = DATABASE_PATH + DATABASE_NAME;
            Log.d(TAG, "In copyDataBase(): outFileName: " + outFileName);
            OutputStream mOutput = new FileOutputStream(outFileName);
            byte[] mBuffer = new byte[1024];
            int mLength;
            while ((mLength = mInput.read(mBuffer))>0) {
                mOutput.write(mBuffer, 0, mLength);
            }
            mOutput.flush();
            mOutput.close();
            mInput.close();
        }

        private boolean checkDataBase()
        {
            File dbFile = new File(DATABASE_PATH + DATABASE_NAME);
            return dbFile.exists();
        }
        
        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(TAG, "copying database assets.");
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