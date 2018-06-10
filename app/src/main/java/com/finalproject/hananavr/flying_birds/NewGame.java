package com.finalproject.hananavr.flying_birds;

import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

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

        inGameBackgroundMusic = MediaPlayer.create(getApplicationContext(),R.raw.ingamebgsound);
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
        inGameBackgroundMusic.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        inGameBackgroundMusic.start();
    }
}
