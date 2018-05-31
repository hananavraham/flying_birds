package com.finalproject.hananavr.flying_birds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void mainMenuClick(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
