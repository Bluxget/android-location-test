package com.jodim.locationtest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class MyLocationService extends Service {

    public final static String ACTION_LOCATION = "location";

    public MyLocationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        MyLocationServiceListener locationServiceListener = new MyLocationServiceListener(this);

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationServiceListener);
        } catch (SecurityException ex) {
            Log.e("location", ex.getMessage());
        }

        return START_STICKY;
    }

    public void notifyLocation(Location location) {
        Intent intent = new Intent(ACTION_LOCATION);

        intent.putExtra("latitude", location.getLatitude());
        intent.putExtra("longitude", location.getLongitude());
        intent.putExtra("altitude", location.getAltitude());

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
