package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class SettingsActivity extends AppCompatActivity implements CheckBox.OnCheckedChangeListener {

    private SeekBar sbVolume;
    private CheckBox cbSfx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        cbSfx = findViewById(R.id.cbSfxSounds);
//        cbSfx.setOnCheckedChangeListener(this);
        sbVolume = findViewById(R.id.skVolumeControl);
    }

    public void mainMenuClick(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
            cbSfx.setText("SFX sounds on");
        else
            cbSfx.setText("SFX sounds off");
    }
}
