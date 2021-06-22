package com.example.sosmeioambiente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ActivityDenuncia extends AppCompatActivity {
    private Spinner spinner;
    private TextView textViewLatitude, textViewLongitude;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipos, android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        textViewLatitude = findViewById(R.id.textViewLatitude);
        textViewLongitude = findViewById(R.id.textViewLongitude);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //Pegar GPS
        if (ContextCompat.checkSelfPermission(ActivityDenuncia.this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(ActivityDenuncia.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ActivityDenuncia.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                textViewLatitude.setText(String.valueOf(location.getLatitude()));
                textViewLongitude.setText(String.valueOf(location.getLongitude()));
            }
        });
    }

    public void navegacaoCadDen(View view) {
        Intent intent = new Intent(ActivityDenuncia.this, ActivityDenunciaProt.class);
        startActivity(intent);
    }
}