package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class InGameMenu {
    private Bitmap igmBackground;
    private Bitmap resumeGame;
    private Bitmap mainMenu;
    private Bitmap restartGame;
    private Bitmap soundsToogleOn;
    private Bitmap soundsToogleOff;

    int optionWidth, optionHeight, soundsToogle;


    public InGameMenu(Context context, Bitmap igmBackground, Bitmap igmResumeGame, Bitmap igmRestartGame, Bitmap igmMainMenu, Bitmap soundsToogleOn, Bitmap soundsToogleOff) {
        this.igmBackground = igmBackground;
        this.resumeGame = igmResumeGame;
        this.restartGame = igmRestartGame;
        this.mainMenu = igmMainMenu;
        this.soundsToogleOn = soundsToogleOn;
        this.soundsToogleOff = soundsToogleOff;

        soundsToogle = 0;
        optionWidth = igmResumeGame.getWidth();
        optionHeight = igmResumeGame.getHeight();
    }

    public int getOptionWidth() {
        return optionWidth;
    }

    public int getOptionHeight() {
        return optionHeight;
    }

    public void draw(Canvas canvas, int soundsToogle){
        canvas.drawBitmap(igmBackground, 750,200,null);
        canvas.drawBitmap(resumeGame,820, 285, null );
        canvas.drawBitmap(restartGame,820, 385, null );
        canvas.drawBitmap(mainMenu,820, 485, null );
        if(soundsToogle == 0){
            canvas.drawBitmap(soundsToogleOn,820, 575, null );
        }
        if(soundsToogle == 1){
            canvas.drawBitmap(soundsToogleOff,820, 575, null );
        }

    }
}
