package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class InGameMenu {
    private Bitmap igmBackground;
    private Bitmap resumeGame;
    private Bitmap mainMenu;
    private Bitmap ExitGame;

    private int optionWidth;
    private int optionHeight;

    public InGameMenu(Context context, Bitmap igmBackground, Bitmap igmResumeGame, Bitmap igmMainMenu, Bitmap igmExitGame) {
        this.igmBackground = igmBackground;
        this.resumeGame = igmResumeGame;
        this.mainMenu = igmMainMenu;
        this.ExitGame = igmExitGame;

        optionWidth = igmResumeGame.getWidth();
        optionHeight = igmResumeGame.getHeight();
    }

    public int getOptionWidth() {
        return optionWidth;
    }

    public int getOptionHeight() {
        return optionHeight;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(igmBackground, 750,200,null);
        canvas.drawBitmap(resumeGame,810, 265, null );
        canvas.drawBitmap(mainMenu,810, 350, null );
        canvas.drawBitmap(ExitGame,810, 435, null );
    }
}
