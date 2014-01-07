/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

import java.io.IOException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.IBinder;
import android.widget.Toast;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 */
public class DatabaseLoaderService extends Service {

    private LootDB db;
    private AssetManager manager;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Service#onBind(android.content.Intent)
     */
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // code to execute when the service is first created
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {
        int r = 0;
        try {
            db = new LootDB(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.open();
        manager = this.getAssets();

        Toast.makeText(this, "Database finished loading!", Toast.LENGTH_LONG)
                .show();

        stopSelf();
        return r;

    }

    @Override
    public void onDestroy() {
        // manager.close();
    }

}
