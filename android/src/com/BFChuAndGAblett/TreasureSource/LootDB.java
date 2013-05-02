/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

import android.content.Context;
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

    public boolean saveEntry(/* needs parameters matching the table */) {
        boolean successfull = false;
        // TODO: save entry code

        return successfull;
    }

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
        private static final String CREATE_TABLE = "";
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