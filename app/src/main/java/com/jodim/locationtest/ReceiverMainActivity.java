package com.jodim.locationtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReceiverMainActivity extends BroadcastReceiver {

    private MainActivity activity;

    public ReceiverMainActivity(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case MyLocationService.ACTION_LOCATION:
                this.activity.displayLocation(intent.getDoubleExtra("latitude", 0), intent.getDoubleExtra("longitude", 0), intent.getDoubleExtra("altitude", 0));
                break;
            case MySensorService.ACTION_SENSOR_TEMPERATURE:
                this.activity.displaySensorTemperature(intent.getDoubleExtra("temperature", 0));
                break;
            case MySensorService.ACTION_SENSOR_LUX:
                this.activity.displaySensorLux(intent.getDoubleExtra("lux", 0));
                break;
        }
    }
}
