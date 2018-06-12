package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements CheckBox.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    CheckBox cbSfx;
    SeekBar skVolume;
    AudioManager audioManager;
    private int volume;
    private boolean IsSFX;

    public Settings(){
        volume = 24;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        skVolume = findViewById(R.id.skVolume);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        skVolume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        skVolume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        skVolume.setOnSeekBarChangeListener(this);
        cbSfx = findViewById(R.id.cbSfx);
        cbSfx.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (cbSfx.isChecked()){
            cbSfx.setText("SFX sounds on");
            IsSFX = true;
        }else{
            cbSfx.setText("SFX sounds off");
            IsSFX = false;
        }
    }

    public int getVolume(){
        return volume;
    }

    public boolean IsSFX(){
        return IsSFX;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
