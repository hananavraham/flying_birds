package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;

public class SettingsActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener , CheckBox.OnCheckedChangeListener{

    private SeekBar sbVolume;
    private CheckBox cbSfxSounds;
    private AudioManager audioManager;
    private ImageView ivSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        sbVolume = findViewById(R.id.sbVolume);
        cbSfxSounds = findViewById(R.id.cbSfxSounds);
        ivSound = findViewById(R.id.ivSound);

        //Setting the seekbar progress to the current phone volume progress
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        sbVolume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        sbVolume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        if(sbVolume.getProgress() == 0)
            ivSound.setImageResource(R.drawable.mute);

        sbVolume.setOnSeekBarChangeListener(this);
        cbSfxSounds.setOnCheckedChangeListener(this);

        cbSfxSounds.setChecked(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("SETTINGS_CB", true));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
        if(progress == 0)
            ivSound.setImageResource(R.drawable.mute);
        if(progress > 0)
            ivSound.setImageResource(R.drawable.sound);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            cbSfxSounds.setText("SFX Sounds ON");
            PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("SETTINGS_CB",true).apply();
        }else{
            cbSfxSounds.setText("SFX Sounds OFF");
            PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("SETTINGS_CB",false).apply();
        }

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
