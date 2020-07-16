package com.example.googlemapsexample;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	private GoogleMap map;
	private static final LatLng MELBOURNE = new LatLng(-37.81319,144.9629);
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		Marker _melbourne = map.addMarker(new MarkerOptions().position(MELBOURNE).title("Melbourne").snippet("Población 4.000.000").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
		
		map.addMarker( new MarkerOptions().position(new LatLng(0,0)).title("Mi primer marker") );
		map.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker arg0) {
				Toast.makeText(getApplicationContext(), arg0.getTitle(), Toast.LENGTH_LONG).show();
				return false;
			}
		});
		
		PolylineOptions rectOptions = new PolylineOptions()
		.add( new LatLng(137.35, -122.0))
		.add( new LatLng(37.45, -122.0))
		.add( new LatLng(37.45, -122.2))
		.add( new LatLng(37.35, -122.2))
		.add( new LatLng(137.35, -122.0))
		;
		
		Polyline polyline = map.addPolyline(rectOptions);
		
		Geocoder geocoder = new Geocoder(this, Locale.getDefault());
		try {
			List<Address> addreses = geocoder.getFromLocation(-37.81319, 144.9629, 1);
			System.out.println(addreses.get(0).getCountryName() );
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
