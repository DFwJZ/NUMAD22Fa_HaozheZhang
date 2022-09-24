package edu.northeastern.numad22fa_haozhezhang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity____";
    private Button clickMe;
    private Button ClickyClicky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Wire up the button to do stuff

        // get the button
        clickMe  = findViewById(R.id.btnClickMe);

        // set what happens when the user clicks
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

        ClickyClicky = findViewById(R.id.clicky_clicky_btn);
        ClickyClicky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Log.v(TAG, "Enter Clicky Clicky...");
                Intent clickyIntent = new Intent(MainActivity.this, ClickyClickyActivity.class);
                startActivity(clickyIntent);
            }
        });
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