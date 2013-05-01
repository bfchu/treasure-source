/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource.Logic;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 */
class LootDBOpenHelper extends SQLiteOpenHelper {

    public static final String KEY_ID = "_id";
    public static final String KEY_LOOT_NAME_COLUMN = "LOOT_NAME_COLUMN";
    public static final String KEY_LOOT_ACCESSIBLE_COLUMN = "LOOT_ACCESSIBLE_COLUMN";
    public static final String KEY_LOOT_VALUE_COLUMN = "LOOT_VALUE_COLUMN";
    private static final String DATABASE_NAME = "lootDatabase.db";
    private static final String DATABASE_TABLE = "lewt";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "
            + DATABASE_TABLE + " (" + KEY_ID
            + " integer primary key autoincrement, ";

    public LootDBOpenHelper() {
        super(null, DATABASE_NAME, null, 0);
        // TODO Auto-generated constructor stub

    }

    public LootDBOpenHelper(Context context, String name,
            CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub

    }

    public Cursor getCursor(String tableName) {
        String[] result_columns = new String[] { KEY_ID,
                KEY_LOOT_ACCESSIBLE_COLUMN, KEY_LOOT_VALUE_COLUMN };
        String where = KEY_LOOT_ACCESSIBLE_COLUMN + "=" + 1;
        String whereArgs[] = null;
        String groupBy = null;
        String having = null;
        String order = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(tableName, result_columns, where, whereArgs,
                groupBy, having, order);
        return cursor;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
}
