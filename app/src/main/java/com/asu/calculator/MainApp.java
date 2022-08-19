package com.asu.calculator;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class MainApp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //configuring window to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);

        // calling handler to run a task for specific time interval
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // creating a new intent
                Intent calc = new Intent(MainApp.this, MainActivity.class);

                // starting a new activity.
                startActivity(calc);
            }
        }, 1600);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //finish splash screen intent
        finish();
    }
}
