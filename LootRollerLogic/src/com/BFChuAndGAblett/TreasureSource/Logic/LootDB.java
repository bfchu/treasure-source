/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource.Logic;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 */
public class LootDB {

    private LootDBOpenHelper db;

    public LootDB() {
        super();
    }

    public LootDB(LootDBOpenHelper db) {
        super();
        this.db = db;
    }

    public LootDB(Context context, String name, CursorFactory factory,
            int version) {
        super();
        this.db = new LootDBOpenHelper(context, name, factory, version);
    }

    public LootDBOpenHelper getDb() {
        return db;
    }

    public void setDb(LootDBOpenHelper db) {
        this.db = db;
    }

    public void printTable(String tableName) {
        Cursor dbCursor = db.getCursor("lewt");

    }

}
