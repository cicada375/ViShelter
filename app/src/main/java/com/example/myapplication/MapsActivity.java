package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.example.myapplication.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    FusedLocationProviderClient fusedLocationProviderClient;
    private int ACCESS_LOCATION_REQUEST_CODE = 10001;
    LocationRequest locationRequest;
    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.myapplication.databinding.ActivityMapsBinding binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // Add a marker in Sydney and move the camera
        //MapStyleOptions styleOptions=MapStyleOptions.loadRawResourceStyle(this, googleMap.setMapStyle(GoogleMap.MAP_TYPE_SATELLITE));
        //LatLng sydney = new LatLng(-34, 151);

        googleMap.setMinZoomPreference(7.5f);

        //googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //new MarkerOptions().position(new LatLng(49.0811115,28.3440991))
        LatLng Vinnitsia = new LatLng(49.102275, 28.675528);


        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Vinnitsia));
        googleMap.addMarker(new MarkerOptions().position(Vinnitsia).title("Вінниця"));
        MapStyleOptions styleOptions = MapStyleOptions.loadRawResourceStyle(this, R.raw.custom_map_style3);//custom_map_style - дефолт, 2- ніч, 3- срібло, 4- темна тема, 5- Aubergine (синя тема)
        googleMap.setMapStyle(styleOptions);
        //цейво, штука щоб синій кружок був на нашій геолокації
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                enableUserLocation();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_LOCATION_REQUEST_CODE);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_LOCATION_REQUEST_CODE);
                }
            }
            return;
        }
        googleMap.setMyLocationEnabled(true);


        //googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //googleMap.setMapStyle(styleOptions);

    }

    private void enableUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation

            // for ActivityCompat#requestPermissions for more details.

        }
        googleMap.setMyLocationEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ACCESS_LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                enableUserLocation();
            }
            else{
                //сказати що дозвіл не надано
            }
        }
    }
}