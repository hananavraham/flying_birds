package com.finalproject.hananavr.flying_birds;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStartGame;
    Button btnSettings;
    Button btnHelp;
    Button btnAbout;
    Button btnExitGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartGame = (Button) findViewById(R.id.btnNewGame);
        btnSettings = (Button) findViewById(R.id.btnSettings);
        btnHelp = (Button) findViewById(R.id.btnHelp);
        btnAbout = (Button) findViewById(R.id.btnAbout);
        btnExitGame = (Button) findViewById(R.id.btnExit);

        setListeners();
    }

    private void setListeners() {
        btnStartGame.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        btnHelp.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnExitGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnNewGame:
                startActivity(new Intent(getApplicationContext(),NewGame.class));
                break;
            case R.id.btnSettings:
                startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
                break;
            case R.id.btnHelp:
                startActivity(new Intent(getApplicationContext(),Help.class));
                break;
            case R.id.btnAbout:
                startActivity(new Intent(getApplicationContext(),About.class));
                break;
            case R.id.btnExit:
                System.exit(0);
                break;
        }
    }
}
