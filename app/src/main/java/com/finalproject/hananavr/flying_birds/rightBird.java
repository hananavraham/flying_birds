package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class rightBird {
    private float posX;
    private float posY;
    private int imageWidth;
    private int imageHeight;
    private int birdSpeedX;
    private int birdSpeedY;
    private int tmpBirdSpeedX;
    private int tmpBirdSpeedY;
    private Bitmap image;
    private int points;
    private boolean isDead;

    public rightBird(Context context, Bitmap bitmap) {
        this.image = bitmap;
        imageWidth = bitmap.getWidth();
        imageHeight = bitmap.getHeight();
        birdSpeedY = 0;
        isDead = false;
    }

    public void setBirdInfo(float x, float y, int speed){
        this.posY = y;
        this.posX = x;
        this.birdSpeedX = speed;
    }

    public int getImageWidth() {
        return imageWidth;
    }


    public int getImageHeight() {
        return imageHeight;
    }

    public float getX(){
        return this.posX;
    }

    public float getY(){
        return this.posY;
    }

    public void setBirdSpeedY(int birdSpeedY) {
        this.birdSpeedY = birdSpeedY;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void pausedState(){
        tmpBirdSpeedX = birdSpeedX;
        tmpBirdSpeedY = birdSpeedY;
        birdSpeedX = 0;
        birdSpeedY = 0;
    }

    public void runningState(){
        birdSpeedX = tmpBirdSpeedX;
        birdSpeedY = tmpBirdSpeedY;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image,posX,posY,null);
        update();
    }

    private void update(){
        this.posX -= birdSpeedX;
        this.posY += birdSpeedY;
    }
}
