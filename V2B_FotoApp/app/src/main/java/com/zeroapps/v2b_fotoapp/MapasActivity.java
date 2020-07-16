package com.zeroapps.v2b_fotoapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapasActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoomLevel = (float) 9.0;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(17.98, -92.91);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Aqui en Visha"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));

        Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(18.0573819, -93.0960703)).icon(BitmapDescriptorFactory.fromResource(R.drawable.pinazul)).title("Cundua"));
        Marker marker2 = mMap.addMarker(new MarkerOptions().position(new LatLng(18.238773, -93.306527)).icon(BitmapDescriptorFactory.fromResource(R.drawable.pinazul)).title("Kepedo"));
    }
}