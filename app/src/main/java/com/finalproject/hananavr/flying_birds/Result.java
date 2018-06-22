package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    int scoreFromCurrentGame;
    int secondsOneness, secondsTenths, minutesOneness, minutesTenths;
    int highscore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();
        scoreFromCurrentGame = extras.getInt("SCORE");
        secondsOneness = extras.getInt("SECONDS_ONENESS");
        secondsTenths = extras.getInt("SECONDS_TENTHS");
        minutesOneness = extras.getInt("MINUTES_ONENESS");
        minutesTenths = extras.getInt("MINUTES_TENTHS");

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        TextView highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);

        scoreLabel.setText(scoreFromCurrentGame + " | "+String.valueOf(minutesTenths)+String.valueOf(minutesOneness)+":"+String.valueOf(secondsTenths)+String.valueOf(secondsOneness));

        SharedPreferences settings = getSharedPreferences("highscore", Context.MODE_PRIVATE);

        //reading highscore from SharedPreferences
        highscore = settings.getInt("highscore", 0);

        if(scoreFromCurrentGame > highscore){
            highScoreLabel.setText("Congratulations! New High Score is: " + scoreFromCurrentGame+ " | "+String.valueOf(minutesTenths)+String.valueOf(minutesOneness)+":"+String.valueOf(secondsTenths)+String.valueOf(secondsOneness));

            // saved highscore via SharedPreferences
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("highscore", scoreFromCurrentGame);
            editor.putInt("minutesTenths",minutesTenths);
            editor.putInt("minutesOneness",minutesOneness);
            editor.putInt("secondsTenths",secondsTenths);
            editor.putInt("secondsOneness",secondsOneness);
            editor.apply();
        }

        else{
            highScoreLabel.setText("High Score is: " + highscore+ " | "+String.valueOf(settings.getInt("minutesTenths", 0))+String.valueOf(settings.getInt("minutesOneness", 0))+":"+String.valueOf(settings.getInt("secondsTenths", 0))+String.valueOf(settings.getInt("secondsOneness", 0)));
        }
    }

    public void tryAgain(View view){
        MainActivity.gameBtnSfxSound.start();
        startActivity(new Intent(getApplicationContext(),NewGame.class));
    }

    public void mainMenuClick(View view){
        MainActivity.gameBtnSfxSound.start();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        NewGame.inGameBackgroundMusic.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        NewGame.inGameBackgroundMusic.start();
    }
}
