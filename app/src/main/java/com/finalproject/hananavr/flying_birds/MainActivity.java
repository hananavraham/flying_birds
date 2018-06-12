package com.finalproject.hananavr.flying_birds;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static MediaPlayer appBgMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

            appBgMusic = MediaPlayer.create(getApplicationContext(), R.raw.buttonclick);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnNewGame:
                appBgMusic.start();
                startActivity(new Intent(getApplicationContext(),NewGame.class));
                break;
            case R.id.btnSettings:
                appBgMusic.start();
                startActivity(new Intent(getApplicationContext(),Settings.class));
                break;
            case R.id.btnHelp:
                appBgMusic.start();
                startActivity(new Intent(getApplicationContext(),Help.class));
                break;
            case R.id.btnAbout:
                appBgMusic.start();
                startActivity(new Intent(getApplicationContext(),About.class));
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
       this.finishAffinity();
    }
}
