package com.tutorial.helloworld;


import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class LocationActivity  extends Activity implements LocationListener {

    private static final long MIN_DISTANCE = 5;
    private static final long MIN_TIME = 10 * 1000;
    private TextView mTextView;
    private LocationManager mLocationManager;
    private String mProvider;

    @Override
      public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        mTextView = (TextView) findViewById(R.id.locationText);

        Criteria criteria = new Criteria();
        criteria.setCostAllowed(false);
        criteria.setAltitudeRequired(false);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mProvider = mLocationManager.getBestProvider(criteria,true);

        Location localization = mLocationManager.getLastKnownLocation(mProvider);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocationManager.requestLocationUpdates(mProvider, MIN_TIME, MIN_DISTANCE, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationManager.removeUpdates(this);
    }
    @Override
    public void onLocationChanged(Location location) {
            mTextView.append(mTextView.getText().toString() + location.toString() + "\n" );
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
