package edu.northeastern.numad22fa_haozhezhang;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity____";
    private Button clickMe;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Wire up the button to do stuff

        // get the button
        clickMe  = findViewById(R.id.AboutmeBtn);

        // set what happens when the user clicks

        // Week 1 About me button
        clickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "This is a magic log message!");
                String studentName = "Name: Haozhe Zhang\n";
                String studentEmail ="Email: zhang.haozhe1@northeastern.edu";
                Toast.makeText(getApplicationContext(), studentName + studentEmail, Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

    // Week 3 adding clickyClicky feature
    public void clickyClicky(View view) {
        Log.v(TAG, "Enter Clicky Clicky...");
        Intent clickyIntent = new Intent(MainActivity.this, ClickyClickyActivity.class);
        startActivity(clickyIntent);
    }

    // Week 4 adding recyclerView of a link collector feature
    public void linkCollector(View view) {
        Log.v(TAG, "Entering link collector...");
        Intent linkCollectorIntent = new Intent(MainActivity.this, LinkCollector.class);
        startActivity(linkCollectorIntent);
    }

    // Week 5 adding Finding Primes feature
    public void findPrimes(View view) {
        Log.v(TAG, "Entering findPrimes activity....");
        Intent findPrimesIntent = new Intent(MainActivity.this, FindPrimes.class);
        startActivity(findPrimesIntent);
    }

    // Week 6 adding Location Confirmed feature
    public void locationConfirmed(View view) {
        setupPermission();

        Intent locationConfirmedActivity = new Intent(MainActivity.this, Location.class);
        startActivity(locationConfirmedActivity);

    }

    public boolean setupPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                new AlertDialog.Builder(this)
                        .setTitle("Suggestion")
                        .setMessage("Precious GPS gives better result.")
                        .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                makeRequest();
                            }
                        })
                        .create()
                        .show();
            } else {
                makeRequest();
            }
            return false;
        } else {
            return true;
        }
    }

    protected void makeRequest() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted
                } else {
//
//                    // permission denied

                }
                return;
            }

        }
    }
    @Override
    protected void onPause() {
        Log.v(TAG, "onPause()");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        Log.v(TAG, "onResume()");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.v(TAG, "onRestart()");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.v(TAG, "onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.v(TAG, "onStop()");
        super.onStop();
    }
}