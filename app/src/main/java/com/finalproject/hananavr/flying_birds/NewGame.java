package com.finalproject.hananavr.flying_birds;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ListIterator;

public class NewGame extends AppCompatActivity implements MediaPlayer.OnPreparedListener {

    Game game;
    static MediaPlayer inGameBackgroundMusic;
    static MediaPlayer arrowShoot;
    static MediaPlayer deadBird;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        game = new Game(this);
        inGameBackgroundMusic = MediaPlayer.create(getApplicationContext(),R.raw.gamemusic);
        inGameBackgroundMusic.setLooping(true);
        inGameBackgroundMusic.setOnPreparedListener(this);

        arrowShoot = MediaPlayer.create(getApplicationContext(),R.raw.shootingarrow);
        deadBird = MediaPlayer.create(getApplicationContext(),R.raw.deadbird);
        deadBird.setVolume(100,100);


        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            game.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.ingamebackground) );
        } else {
            game.setBackground(ContextCompat.getDrawable(this, R.drawable.ingamebackground));
        }
        setContentView(game);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        inGameBackgroundMusic.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //This line crashes the game if we restart or going back to main menu
        //inGameBackgroundMusic.pause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        inGameBackgroundMusic.start();
    }


    //The 3 below methods needs to deal with pausing the game correctly when the game is minimized or back button is pressed
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //inGameBackgroundMusic.stop();
        //startActivity(new Intent(getApplicationContext(),MainActivity.class));
        pauseGame();
    }

    @Override
    protected void onUserLeaveHint() {
        pauseGame();
        super.onUserLeaveHint();
    }

    private void pauseGame(){
            inGameBackgroundMusic.pause();
            game.pause_flg = 1;
    }


}
