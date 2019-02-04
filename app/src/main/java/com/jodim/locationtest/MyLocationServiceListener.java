package com.jodim.locationtest;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class MyLocationServiceListener implements LocationListener {

    private MyLocationService service;

    public MyLocationServiceListener(MyLocationService service) {
        this.service = service;
    }

    public void onLocationChanged(Location location) {
        Log.d("location", location.toString());

        //this.activity.updateLocation(location);
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onProviderDisabled(String provider) {
    }
}
