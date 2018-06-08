package com.finalproject.hananavr.flying_birds;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class NewGame extends AppCompatActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        game = new Game(this);

        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            game.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.ingamebackground) );
        } else {
            game.setBackground(ContextCompat.getDrawable(this, R.drawable.ingamebackground));
        }
        setContentView(game);
    }

}
