/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

import java.io.IOException;
import java.util.Timer;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 */
public class DatabaseLoaderService extends Service {

    private final int UPDATE_INTERVAL = 60 * 1000;
    private Timer timer = new Timer();
    private static final int NOTIFICATION_EX = 1;
    private NotificationManager notificationManager;
    private LootDB db;

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        db.open();
        try {
            db.populateTables();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Toast.makeText(this, "Database finished loading!", Toast.LENGTH_LONG)
                .show();

        // stopSelf();
        return r;

    }

    @Override
    public void onDestroy() {
        if (timer != null) {
            timer.cancel();
        }
    }

}
