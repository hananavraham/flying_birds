package com.finalproject.hananavr.flying_birds;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.ListIterator;

public class Result extends AppCompatActivity {
    int scoreFromCurrentGame;
    int highscore =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreFromCurrentGame = getIntent().getIntExtra("SCORE",0);

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        TextView highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);

        scoreLabel.setText(scoreFromCurrentGame + "");

        SharedPreferences settings = getSharedPreferences("highscore", Context.MODE_PRIVATE);

        //reading highscore from SharedPreferences
        highscore = settings.getInt("highscore", 0);

        if(scoreFromCurrentGame > highscore){
            highScoreLabel.setText("Congratulations! New High Score is: " + scoreFromCurrentGame);

            // saved highscore via SharedPreferences
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("highscore", scoreFromCurrentGame);
            editor.commit();
        }

        else{
            highScoreLabel.setText("High Score is: " + highscore);
        }
    }

    public void tryAgain(View view){
        startActivity(new Intent(getApplicationContext(),NewGame.class));
    }

    public void mainMenuClick(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
