package com.finalproject.hananavr.flying_birds;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends AppCompatActivity {
    int highscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        TextView highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);


        // creating database instnace
        AppDataBase db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class,"score")
                .allowMainThreadQueries()
                .build();
        //db.high_scoreDao().insertHighScore(new High_Score(50));
        highscore = db.high_scoreDao().getHighScore();     // get highscore


        //Game.score = getIntent().getIntExtra("SCORE",0);
        scoreLabel.setText(Game.score + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);

        //Game.highScore = settings.getInt("HIGE_SCORE",0);

        if(Game.score > NewGame.highScore){
            highScoreLabel.setText("High Score : " + Game.score);
            NewGame.highScore = Game.score;
            db.high_scoreDao().updateHighScore(new High_Score(highscore));
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", Game.score);
            editor.commit();
        }

        else{
            highScoreLabel.setText("High Score : " + NewGame.highScore);
        }
    }

    public void tryAgain(View view){
        startActivity(new Intent(getApplicationContext(),NewGame.class));
    }

    public void mainMenuClick(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
