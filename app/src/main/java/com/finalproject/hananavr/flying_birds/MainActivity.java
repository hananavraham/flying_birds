package com.finalproject.hananavr.flying_birds;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnPreparedListener {
    static MediaPlayer gameBtnSfxSound;
    static MediaPlayer gameMenuBgMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        gameBtnSfxSound = MediaPlayer.create(getApplicationContext(), R.raw.buttonclick);
        gameMenuBgMusic = MediaPlayer.create(getApplicationContext(), R.raw.ingamebgsound);
        gameMenuBgMusic.setLooping(true);
        gameMenuBgMusic.setOnPreparedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnNewGame:
                gameBtnSfxSound.start();
                startActivity(new Intent(getApplicationContext(),NewGame.class));
                break;
            case R.id.btnHelp:
                gameBtnSfxSound.start();
                startActivity(new Intent(getApplicationContext(),Help.class));
                break;
            case R.id.btnAbout:
                gameBtnSfxSound.start();
                startActivity(new Intent(getApplicationContext(),About.class));
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
       this.finishAffinity();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        gameMenuBgMusic.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameMenuBgMusic.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameMenuBgMusic.start();
    }
}
