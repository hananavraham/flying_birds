package com.finalproject.hananavr.flying_birds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class Settings extends AppCompatActivity implements CheckBox.OnCheckedChangeListener {

    CheckBox cbSfx;
    SeekBar skVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        skVolume = findViewById(R.id.skVolume);
        cbSfx = findViewById(R.id.cbSfx);
        cbSfx.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (cbSfx.isChecked()){
            cbSfx.setText("SFX sounds on");
        }else{
            cbSfx.setText("SFX sounds off");
        }
    }

    public void mainMenuClick(View view){
        MainActivity.appBgMusic.start();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
