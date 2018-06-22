package com.finalproject.hananavr.flying_birds;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

public class NewGame extends AppCompatActivity implements MediaPlayer.OnPreparedListener {

    Game game;
    static MediaPlayer inGameBackgroundMusic;
    static MediaPlayer arrowShoot;
    static MediaPlayer deadBird;
    static int backpressedFlg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        game = new Game(this);
        backpressedFlg = 0;
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
        newGame();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        inGameBackgroundMusic.start();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        //This line crashes the game if we restart or going back to main menu
//        //inGameBackgroundMusic.pause();
//    }


    /*The below methods deal with back & home buttons presss*/

    @Override
    protected void onResume() {
        super.onResume();
        inGameBackgroundMusic.start();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(backpressedFlg == 0){
            backpressedFlg = 1;
            pauseGame();
        }
        else{
            backpressedFlg = 0;
            unPauseGame();
        }
    }

    @Override
    protected void onUserLeaveHint() {
        pauseGame();
        inGameBackgroundMusic.pause();
        backpressedFlg = 1;
        super.onUserLeaveHint();
    }

    private void newGame(){
        if(Game.birds.size() > 0)
            Game.birds.clear();
    }

    private void pauseGame(){
        game.pause_flg = 1;
        Game.future.cancel(true);
        SurvivalTimer.timerFuture.cancel(true);
        ListIterator it = Game.birds.listIterator();
        while(it.hasNext()){
            Bird bird = (Bird) it.next();
            bird.pausedState();
        }
    }

    private void unPauseGame(){
        game.pause_flg = 0;
        Game.future = Game.service.scheduleAtFixedRate(Game.runnable, 1, 3, TimeUnit.SECONDS);
        SurvivalTimer.timerFuture = SurvivalTimer.timerService.scheduleAtFixedRate(SurvivalTimer.timerRunnable, 1, 1, TimeUnit.SECONDS);
        ListIterator it = Game.birds.listIterator();
        while(it.hasNext()){
            Bird bird = (Bird) it.next();
            bird.runningState();
        }
    }

}
