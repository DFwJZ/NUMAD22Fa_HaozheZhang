package edu.northeastern.numad22fa_haozhezhang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DemoInitialApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Wire up the button to do stuff

        // get the button
        Button btn = (Button) findViewById(R.id.btnClickMe);

        // set what happens when the user clicks
        btn.setOnClickListener(new View.OnClickListener() {
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


}