package com.finalproject.hananavr.flying_birds;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnPreparedListener {
    static MediaPlayer gameBtnSfxSound;
    static MediaPlayer gameMenuBgMusic;
    private boolean isSfxOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        gameBtnSfxSound = MediaPlayer.create(getApplicationContext(), R.raw.buttonclick);
        gameMenuBgMusic = MediaPlayer.create(getApplicationContext(), R.raw.ingamebgsound);
        gameMenuBgMusic.setLooping(true);
        gameMenuBgMusic.setOnPreparedListener(this);

        isSfxOn = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("SETTINGS_CB", true);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnNewGame:
                if(isSfxOn)
                    gameBtnSfxSound.start();
                startActivity(new Intent(getApplicationContext(),NewGame.class));
                break;
            case R.id.btnSettings:
                if(isSfxOn)
                    gameBtnSfxSound.start();
                startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
                break;
            case R.id.btnHelp:
                if(isSfxOn)
                    gameBtnSfxSound.start();
                startActivity(new Intent(getApplicationContext(),Help.class));
                break;
            case R.id.btnAbout:
                if(isSfxOn)
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
        isSfxOn = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("SETTINGS_CB", true);
    }

}
