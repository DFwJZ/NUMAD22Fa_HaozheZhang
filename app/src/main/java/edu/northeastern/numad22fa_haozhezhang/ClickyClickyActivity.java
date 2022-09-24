package edu.northeastern.numad22fa_haozhezhang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ClickyClickyActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ClickyActivity____";
    private Button buttonA;
    private Button buttonB;
    private Button buttonC;
    private Button buttonD;
    private Button buttonE;
    private Button buttonF;

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_clicky);

        buttonA = findViewById(R.id.buttonA);
        buttonA.setOnClickListener(this);

        buttonB = findViewById(R.id.buttonB);
        buttonB.setOnClickListener(this);

        buttonC = findViewById(R.id.buttonC);
        buttonC.setOnClickListener(this);

        buttonD = findViewById(R.id.buttonD);
        buttonD.setOnClickListener(this);

        buttonE = findViewById(R.id.buttonE);
        buttonE.setOnClickListener(this);

        buttonF = findViewById(R.id.buttonF);
        buttonF.setOnClickListener(this);

        tv = findViewById(R.id.Letter_Displayed);

    }

    @Override
    public void onClick(View view){
        switch(view.getId()) {
            case R.id.buttonA:
                tv.setText("Pressed: A");
                break;
            case R.id.buttonB:
                tv.setText("Pressed: B");
                break;
            case R.id.buttonC:
                tv.setText("Pressed: C");
                break;
            case R.id.buttonD:
                tv.setText("Pressed: D");
                break;
            case R.id.buttonE:
                tv.setText("Pressed: E");
                break;
            case R.id.buttonF:
                tv.setText("Pressed: F");
                break;
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