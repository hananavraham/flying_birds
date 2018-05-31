package com.finalproject.hananavr.flying_birds;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newGameClick(View view){
        startActivity(new Intent(getApplicationContext(),new_game.class));
    }

    public void aboutClick(View view){
        startActivity(new Intent(getApplicationContext(),about.class));
    }

    public void helpClick(View view){
        startActivity(new Intent(getApplicationContext(),help.class));
    }
}
