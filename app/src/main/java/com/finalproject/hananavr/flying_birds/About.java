package com.finalproject.hananavr.flying_birds;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.gameMenuBgMusic.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.gameMenuBgMusic.start();
    }
}
