package edu.northeastern.numad22fa_haozhezhang;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Objects;

public class LocationConfirmed extends AppCompatActivity {
    private String TAG = "Starting location activity...";
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 101;
    TextView lat_display, lon_display, distance_travelled_display;
    Switch switch_locationUpdates;
    private static final int DEFAULT_UPDATE_INTERVAL = 5;
    private static final int FAST_UPDATE_INTERVAL = 2;

    // Variable to remember if we are tracking location or not.
    boolean updateOn = false;

    // Location request is a config file fore all settings relate to FusedLocationProviderClient
    LocationRequest.Builder locationBuilder;
    LocationRequest locationRequest;

    // Google's API for location services.
    FusedLocationProviderClient fusedLocationProviderClient;

    // location callback
    LocationCallback locationCallback;

    // distance travelled
    double distance_recorded = 0;

    Location lastLocationReceived;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_confirmed);

        lat_display = findViewById(R.id.latitude_display);
        lon_display = findViewById(R.id.longitude_display);
        distance_travelled_display = findViewById(R.id.distance_travelled_display);
        switch_locationUpdates = findViewById(R.id.location_updates);

        // switch is checked and location is updated upon entering the activity;
        switch_locationUpdates.setChecked(true);

        // set all properties of LocationRequest using LocationRequest.Builder.
        // the fastest allowed interval of location updates. Location updates may arrive faster than the desired interval
        locationBuilder = new LocationRequest.Builder(1000 * DEFAULT_UPDATE_INTERVAL);

        locationBuilder.setMinUpdateIntervalMillis(1000 * FAST_UPDATE_INTERVAL);

        locationBuilder.setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY);

        locationRequest = locationBuilder.build();

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);


                double lat = lastLocationReceived.getLatitude();
                double lon = lastLocationReceived.getLongitude();
                Log.i(TAG, String.format(" callback ---- lat: %f, lon: %f", lat, lon));

                // save the location
                Log.i(TAG, "updating ui in callback");
                updateUIValues(Objects.requireNonNull(locationResult.getLastLocation()));
            }
        };

//        // extra feature
//        switch_locationUpdates.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (switch_locationUpdates.isChecked()) {
//                    locationBuilder.setPriority(Priority.PRIORITY_HIGH_ACCURACY);
//
//                } else {
//                    locationBuilder.setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY);
//                }
//            }
//        });

        switch_locationUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switch_locationUpdates.isChecked()) {
                    // turn on location tracking.
                    startLocationUpdates();
                } else {
                    // turn off location tracking.
                    stopLocationUpdates();
                }
            }
        });

        updateGPS();
        startLocationUpdates();
    }


    private void startLocationUpdates() {
//        Toast.makeText(this,
//                        "GPS getting updates.", Toast.LENGTH_LONG)
//                .show();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);

        updateGPS();
    }

    private void stopLocationUpdates() {
//        Toast.makeText(this,
//                        "GPS updates turned off.", Toast.LENGTH_LONG)
//                .show();

        lat_display.setText("Not tracking location......");
        lon_display.setText("Not tracking location......");

        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateGPS();
                } else {
                    Toast.makeText(this,
                            "This activity requres permission to be granted to work.", Toast.LENGTH_LONG)
                            .show();
                }
                break;
        }
    }


    private void updateGPS() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
//                    Log.i(TAG, "updating ui in updateGPS");
                    updateUIValues(Objects.requireNonNull(location));
                }

            });
        } else {
            // permissions not granted yet.
            // minimum version has to be build O(26) Oreo
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                requestPermissions(new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    private void updateUIValues(Location currentLocation) {
        // update text view objects
//        Toast.makeText(this,
//                        "updating locations", Toast.LENGTH_LONG)
//                .show();

        double lat = currentLocation.getLatitude();
        double lon = currentLocation.getLongitude();
        Log.i(TAG, String.format("updating UI --- lat: %f, lon: %f", lat, lon));
        if (lastLocationReceived != null) {
            if (currentLocation.distanceTo(lastLocationReceived) > 8) {
                distance_recorded += currentLocation.distanceTo(lastLocationReceived);
                Log.i(TAG, String.valueOf(distance_recorded));
            }
        } else {
            lastLocationReceived = currentLocation;
        }

        lat_display.setText("Latitude: " + String.valueOf(currentLocation.getLatitude()));
        lon_display.setText("Longitude: " + String.valueOf(currentLocation.getLongitude()));
        distance_travelled_display.setText(String.format("Distance: %.2f m", distance_recorded));

    }

    public void resetClicked(View view) {
        distance_travelled_display.setText("Distance: 0.00 m");
    }


}