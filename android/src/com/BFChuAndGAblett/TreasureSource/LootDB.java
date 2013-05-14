/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

import java.io.IOException;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
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

    private SQLiteDatabase db;
    private Context context;
    private LootDatabaseOpenHelper dbHelper;

    public LootDB(Context context) throws IOException {
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

    public boolean saveEntryItemTypes(String tableName, Integer id, int dLow,
            int dHigh, String itemName, Double valueAdjust) {
        boolean successfull = false;
        if (id == null) {
            Log.d(TAG, "Creating a new entry: dLow: " + dLow + ", dHigh: "
                    + dHigh + ", item Name: " + itemName + ", Value: "
                    + valueAdjust);
            // create

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
            Log.d(TAG, "updating a new entry: dLow: " + dLow + ", dHigh: "
                    + dHigh + ", item Name: " + itemName + ", Value: "
                    + valueAdjust);
            // create

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

    private void saveEntryEnhancement(String tableName, Integer id,
            Integer dLow, Integer dHigh, Integer enhancement,
            Integer numAbilities, Integer abilityLevel, Integer isSpecific) {
        boolean successfull = false;
        if (id == null) {
            Log.d(TAG, "Creating a new entry: dLow: " + dLow + ", dHigh: "
                    + dHigh + ", enhancement: " + enhancement
                    + ", numAbilities: " + numAbilities + ", abilityLevel: "
                    + abilityLevel + ", isSpecific: " + isSpecific);
            // create

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
            Log.d(TAG, "updating a new entry: dLow: " + dLow + ", dHigh: "
                    + dHigh + ", enhancement: " + enhancement
                    + ", numAbilities: " + numAbilities + ", abilityLevel: "
                    + abilityLevel + ", isSpecific: " + isSpecific);
            // create

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
            Log.d(TAG, "Creating a new entry: dLow: " + dLow + ", dHigh: "
                    + dHigh + ", ability: " + ability + ", priceAdjust: "
                    + priceAdjust);

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
            Log.d(TAG, "updating a new entry: dLow: " + dLow + ", dHigh: "
                    + dHigh + ", ability: " + ability + ", priceAdjust: "
                    + priceAdjust);

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
            Log.d(TAG, "Creating a new entry: dLow: " + dLow + ", dHigh: "
                    + dHigh + ", itemName: " + itemName + ", price: " + price);

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
            Log.d(TAG, "updating a new entry: dLow: " + dLow + ", dHigh: "
                    + dHigh + ", itemName: " + itemName + ", price: " + price);

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

    // TODO: remaining saveEntry forms;

    /**
     * DATABASE CALLERS
     * */
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
        String tableName = "Abilities_" + itemType + "plus" + abilityLevel;

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

    /**
     * DATABASE TABLE POPULATION
     * */
    public void populateTables() throws IOException {
        /*
         * TODO: this method will handle all of the population of tables that
         * are created in initTables().
         */
        LootIO tableFiles = new LootIO("ArmorTypes.dat");
        // needs to construct a group of data, then push it into a
        // saveEntry() that is tailored to that table. much like
        // initTables(), it is probably best to create a function for each
        // group of tables. Tables that have the exact same columns can be
        // done in the same function, using the same methods of generating
        // table names as initTables();

        // TODO: popTable methods for coins, goods, items by APL;

        popItemTypeTable("Armor");
        popItemTypeTable("Shield");
        popItemTypeTable("Weapon");
        popItemTypeTable("RangedWeapon");
        popItemTypeTable("Ammo");
        popItemTypeTable("WondrousItem");

        // Armor Enhancement and Abilities numbers by rarity
        popEnhancementTable("Armor");
        popEnhancementTable("Weapons");

        // Special Abilities for Armor, Shield, Weapons:
        popAbilitiesTable("Armor");
        popAbilitiesTable("Shields");
        popAbilitiesTable("Weapons");
        popAbilitiesTable("Ranged_Weapons");
        popAbilitiesTable("Ammunition");

        // Specific items
        popSpecificItemTable("Armor");
        popSpecificItemTable("Shields");
        popSpecificItemTable("Weapons");
        popSpecificItemTable("Potions");
        popSpecificItemTable("Rings");
        popSpecificItemTable("Rods");
        popSpecificItemTable("Staves");

        popSpecificItemTable("Wondrous_Belt");
        popSpecificItemTable("Wondrous_Body");
        popSpecificItemTable("Wondrous_Chest");
        popSpecificItemTable("Wondrous_Eyes");
        popSpecificItemTable("Wondrous_Feet");
        popSpecificItemTable("Wondrous_Hands");
        popSpecificItemTable("Wondrous_Head");
        popSpecificItemTable("Wondrous_Headband");
        popSpecificItemTable("Wondrous_Neck");
        popSpecificItemTable("Wondrous_Shoulders");
        popSpecificItemTable("Wondrous_Wrists");
        popSpecificItemTable("Wondrous_Slotless");
    }

    public void popItemTypeTable(String tableType) throws IOException {
        String tableName = (tableType + "Types");
        String fileName = (tableName + ".dat");
        AssetManager manager = context.getAssets();
        LootIO tableFiles = new LootIO(manager.open(fileName));

        Integer dLow = 1;
        Integer dHigh = 100;
        String itemType = null;
        Double valueAdjust = 1.0;

        tableFiles.getIn().readLine();// cut off the header line of the file
        while (tableFiles.getIn().ready()) {
            String[] data = getLine(tableFiles);

            dLow = Integer.parseInt(data[1]);
            dHigh = Integer.parseInt(data[2]);
            itemType = data[3];
            valueAdjust = Double.valueOf(data[4]);

            saveEntryItemTypes(tableName, null, dLow, dHigh, itemType,
                    valueAdjust);
        }

        Log.d(TAG, "Populating Table " + tableName);
    }

    public void popEnhancementTable(String tableType) throws IOException {
        String tableName = null;
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
                tableName = (tableType + "_" + lesserOrGreater + "_" + rarityLevel);
                Log.d(TAG, "Populating Table " + tableName);
            }
        }
        String fileName = ("sqlitedatabasetables/" + tableName + ".dat");
        AssetFileDescriptor descriptor = context.getAssets().openFd(fileName);
        LootIO tableFiles = new LootIO(descriptor);

        Integer dLow = 1;
        Integer dHigh = 100;
        Integer enhancement = 1;
        Integer numAbilities = 0;
        Integer abilityLevel = 0;
        Integer isSpecific = 0;

        tableFiles.getIn().readLine();// cut off the header line of the file
        while (tableFiles.getIn().ready()) {
            String[] data = getLine(tableFiles);

            dLow = Integer.valueOf(data[1]);
            dHigh = Integer.valueOf(data[2]);
            enhancement = Integer.valueOf(data[3]);
            numAbilities = Integer.valueOf(data[4]);
            abilityLevel = Integer.valueOf(data[5]);
            isSpecific = Integer.valueOf(data[6]);

            saveEntryEnhancement(tableName, null, dLow, dHigh, enhancement,
                    numAbilities, abilityLevel, isSpecific);
        }
    }

    public void popAbilitiesTable(String tableType) throws IOException {
        String tableName = null;
        for (int ii = 0; ii < 5; ii++) {
            tableName = tableType + "_plus" + (ii + 1);
            Log.d(TAG, "Populating Table " + tableName);
        }
        String fileName = ("sqlitedatabasetables/" + tableName + ".dat");
        AssetFileDescriptor descriptor = context.getAssets().openFd(fileName);
        LootIO tableFiles = new LootIO(descriptor);

        Integer dLow = 1;
        Integer dHigh = 100;
        String ability = null;
        Double priceAdjust = 1.0;

        tableFiles.getIn().readLine();// cut off the header line of the file
        while (tableFiles.getIn().ready()) {
            String[] data = getLine(tableFiles);

            dLow = Integer.valueOf(data[1]);
            dHigh = Integer.valueOf(data[2]);
            ability = data[3];
            priceAdjust = Double.valueOf(data[4]);

            saveEntryAbilities(tableName, null, dLow, dHigh, ability,
                    priceAdjust);
        }

    }

    public void popSpecificItemTable(String tableType) throws IOException {
        String tableName = null;
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
                tableName = ("Specific_" + tableType + "_" + lesserOrGreater
                        + "_" + rarityLevel);
                Log.d(TAG, "Populating Table " + tableName);
            }
        }
        String fileName = ("sqlitedatabasetables/" + tableName + ".dat");
        AssetFileDescriptor descriptor = context.getAssets().openFd(fileName);
        LootIO tableFiles = new LootIO(descriptor);

        Integer dLow = 1;
        Integer dHigh = 100;
        String itemName = null;
        Double price = 1.0;

        tableFiles.getIn().readLine();// cut off the header line of the file
        while (tableFiles.getIn().ready()) {
            String[] data = getLine(tableFiles);

            dLow = Integer.valueOf(data[1]);
            dHigh = Integer.valueOf(data[2]);
            itemName = data[3];
            price = Double.valueOf(data[4]);

            saveEntrySpecificItems(tableName, null, dLow, dHigh, itemName,
                    price);
        }

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
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS contact";

        public LootDatabaseOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public LootDatabaseOpenHelper(Context context, String name,
                CursorFactory factory, int version) {
            super(context, name, factory, version);

        }

        public void initTables(SQLiteDatabase db) {
            // Primary output table
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
                    + "dHigh int, " + "itemType varchar(50), "
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