package com.jodim.locationtest;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class MyLocationListener implements LocationListener {

    private MapViewActivity activity;

    public MyLocationListener(MapViewActivity activity) {
        this.activity = activity;
    }

    public void onLocationChanged(Location location) {
        //Log.d("location", location.toString());

        this.activity.updateLocation(location);
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onProviderDisabled(String provider) {
    }
}
