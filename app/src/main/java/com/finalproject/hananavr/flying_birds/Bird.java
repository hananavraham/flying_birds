package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class Bird {
    private float posX;
    private float posY;
    private int imageWidth;
    private int imageHeight;
    private int birdSpeedX;
    private int birdSpeedY;
    private int tmpBirdSpeedX;
    private int tmpBirdSpeedY;
    private Bitmap image;
    private Bitmap deadImage;
    private boolean IsDead;
    private boolean IsRightDirection;


    public Bird(Context context, boolean RightDirection){
        birdSpeedY = 0;
        IsDead = false;
        IsRightDirection = RightDirection;
    }

    public void setBirdInfo(float x, float y, int speed){
        this.posY = y;
        this.posX = x;
        this.birdSpeedX = speed;
        this.imageWidth = image.getWidth();
        this.imageHeight = image.getHeight();
    }


    public int getImageWidth() { return imageWidth; }

    public int getImageHeight() { return imageHeight; }

    public boolean IsRightDirection() { return IsRightDirection; }

    public float getX(){ return this.posX; }

    public float getY(){ return this.posY; }

    public void setBirdSpeedY(int birdSpeedY) {
        this.birdSpeedY = birdSpeedY;
    }

    public void setImages(Bitmap image, Bitmap deadImage) {
        this.image = image;
        this.deadImage = deadImage;
    }

    public boolean IsDead() { return IsDead; }

    public void setDead(boolean dead) { IsDead = dead; }



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
        if (!this.IsDead)
            canvas.drawBitmap(image,posX,posY,null);
        else
            canvas.drawBitmap(deadImage,posX,posY,null);
        update();
    }

    private void update(){
        this.posX -= birdSpeedX;
        this.posY += birdSpeedY;
    }
}
