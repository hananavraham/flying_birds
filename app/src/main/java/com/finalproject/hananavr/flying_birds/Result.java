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

import java.util.List;

public class Result extends AppCompatActivity {
    List<High_Score> highscore;
    int scoreFromCurrentGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreFromCurrentGame = getIntent().getIntExtra("SCORE",0);

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        TextView highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);


        // creating database instnace
        AppDataBase db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class,"score")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        db.high_scoreDao().insertHighScore(new High_Score(50));
        highscore = db.high_scoreDao().getHighScore();     // get highscore - THIS LINE CRASHED THE GAME -


        //Game.score = getIntent().getIntExtra("SCORE",0);
        scoreLabel.setText(scoreFromCurrentGame + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);

        //Game.highScore = settings.getInt("HIGE_SCORE",0);

        //NewGame.highScore
        if(scoreFromCurrentGame > highscore.get(0).getHighScore()){
            highScoreLabel.setText("Congratulations! New High Score is: " + scoreFromCurrentGame);
            NewGame.highScore = scoreFromCurrentGame;
            db.high_scoreDao().updateHighScore(new High_Score(scoreFromCurrentGame));
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", scoreFromCurrentGame);
            editor.commit();
        }

        else{
            highScoreLabel.setText(" Previous High Score is: " + highscore.get(0).getHighScore());
        }
    }

    public void tryAgain(View view){
        startActivity(new Intent(getApplicationContext(),NewGame.class));
    }

    public void mainMenuClick(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
