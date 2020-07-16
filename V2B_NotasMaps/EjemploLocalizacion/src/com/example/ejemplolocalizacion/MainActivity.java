package com.example.ejemplolocalizacion;

import java.util.List;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.app.Activity;

import android.content.Context;
import android.view.Menu;

public class MainActivity extends Activity {

	private LocationManager locManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		registerLocations();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void initLocation() {

		locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		System.out.println(String.valueOf(loc.getLatitude()));
	}

	private void getListProviders() {
		locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		List<String> listaProviders = locManager.getAllProviders();

		String mejorProvider = locManager.getBestProvider(getBestCriteria(), false);
		System.out.println(mejorProvider);
		LocationProvider provider = locManager.getProvider(listaProviders.get(0));
		System.out.println(provider.getAccuracy());
		System.out.println(provider.supportsAltitude());
		System.out.println(provider.getPowerRequirement());

	}

	private Criteria getBestCriteria() {
		Criteria req = new Criteria();
		req.setAccuracy(Criteria.ACCURACY_FINE);
		req.setAltitudeRequired(true);
		return req;
	}

	private boolean isGPSAvailable() {
		locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if ( ! locManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
			return false;
		else
			return true;
	}
	
	private void registerLocations() {
		locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000,0,new MyLocationListener()  );
	}
	
	private class MyLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
		System.out.println("La posición ha cambiado");
			
		}

		@Override
		public void onProviderDisabled(String provider) {
			
			
		}

		@Override
		public void onProviderEnabled(String provider) {
		
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		
			
		}
		
	}

}
