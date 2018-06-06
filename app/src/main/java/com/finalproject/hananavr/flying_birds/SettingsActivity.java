package com.finalproject.hananavr.flying_birds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class SettingsActivity extends AppCompatActivity {

    SeekBar sbVolume;
    CheckBox cbSfx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sbVolume = findViewById(R.id.skVolumeControl);
        cbSfx = findViewById(R.id.cbSfxSounds);

        cbSfx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    cbSfx.setText("SFX sounds on");
                else
                    cbSfx.setText("SFX sounds off");
            }
        });
    }

}