//для редагування виду вікна графічно відкрий activity_map_xml
//якщо побачите там сіру балку то так і має бути бо це я так пробую зробити набір інструментів
//п.с. там ще за тулбаром цим кнопка стоїть котра має наводитись на нашу геолокацію, якщо проблем з нею не буде то можна буде не чіпати, але сумніваюсь
//що з таким костилем все нормально працюватиме
package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    //FusedLocationProviderClient fusedLocationProviderClient;
    private final int ACCESS_LOCATION_REQUEST_CODE = 10001;
    //LocationRequest locationRequest;
    GoogleMap googleMap;
    Button button;
    Button button2;
    Button button3;
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
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(getBaseContext(),); кароче тут треба визначити функціонал кнопок бо я цейво забув
                //startActivity(intent) і так само з 3-4ма іншими кнопками
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {//тут настройки будуть відкриватися тому нове вікно буду робити
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(getBaseContext(),);
                //startActivity(intent);
            }
        });
        googleMap.setMinZoomPreference(7.5f);
        LatLng Vinnitsia = new LatLng(49.102275, 28.675528);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Vinnitsia));
        googleMap.addMarker(new MarkerOptions().position(Vinnitsia).title("Вінниця"));
        MapStyleOptions styleOptions = MapStyleOptions.loadRawResourceStyle(this, R.raw.custom_map_style3);//custom_map_style - дефолт, 2- ніч, 3- срібло, 4- темна тема, 5- Aubergine (синя тема)
        googleMap.setMapStyle(styleOptions);//

        googleMap.addMarker(new MarkerOptions().position(new LatLng(49.3084452, 28.4944493)));
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

    }

    private void enableUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
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
                //сказати що дозвіл було не надано
            }
        }
    }
}

