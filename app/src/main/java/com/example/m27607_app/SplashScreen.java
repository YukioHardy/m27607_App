package com.example.m27607_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SplashScreen extends AppCompatActivity {

    private static final String TAG = SplashScreen.class.getSimpleName();

    //Creates a new Activity that is shown first
    //It disappiers after 4 seconds and shows the main Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("TAG: " + TAG);
        Log.d(TAG, "Loading methods");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();
        Log.d(TAG, "Creating new Thread...");
        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(4000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent newIntent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(newIntent);
                    finish();
                }
            }
        };
        thread.start();
    }

}