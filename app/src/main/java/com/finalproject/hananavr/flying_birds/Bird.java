package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

public class Bird extends View {
    private float posX;
    private float posY;
    private int imageWidth;
    private int imageHeight;
    private int birdSpeedX;
    private int birdSpeedY;
    private int tmpBirdSpeedX;
    private int tmpBirdSpeedY;
    protected int score;
    protected int requiredClicksToKill;
    protected int currentClicksCounter;
    protected int imageFlg;
    protected int imageFlg2;
    protected Bitmap aliveWingsUpImage;
    protected Bitmap aliveWingsDownImage;
    protected Bitmap deadImage;
    private boolean IsDead;
    private boolean IsRightDirection;


    public Bird(Context context, boolean RightDirection){
        super(context);
        birdSpeedY = 0;
        IsDead = false;
        IsRightDirection = RightDirection;
        currentClicksCounter = 0;
        imageFlg = 1;
        imageFlg2 = 0;
    }

    public void setBirdInfo(float x, float y, int speed){
        this.posY = y;
        this.posX = x;
        this.birdSpeedX = speed;
        this.imageWidth = aliveWingsUpImage.getWidth();
        this.imageHeight = aliveWingsUpImage.getHeight();
    }


    public int getImageWidth() { return imageWidth; }

    public int getImageHeight() { return imageHeight; }

    public boolean IsRightDirection() { return IsRightDirection; }

    public float getX(){ return this.posX; }

    public float getY(){ return this.posY; }

    public void setBirdSpeedY(int birdSpeedY) {
        this.birdSpeedY = birdSpeedY;
    }

    public boolean IsDead() { return IsDead; }

    public void setDead(boolean dead) { IsDead = dead; }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRequiredClicksToKill() {
        return requiredClicksToKill;
    }

    public void setRequiredClicksToKill(int requiredClicksToKill) {
        this.requiredClicksToKill = requiredClicksToKill;
    }

    public int getCurrentClicksCounter() {
        return currentClicksCounter;
    }

    public void setCurrentClicksCounter(int currentClicksCounter) {
        this.currentClicksCounter = currentClicksCounter;
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
        super.draw(canvas);
        if (!this.IsDead){
            if(imageFlg==1){
                canvas.drawBitmap(aliveWingsUpImage,posX,posY,null);
                imageFlg2++;
                if(imageFlg2==10)
                    imageFlg=0;
            }
            else if(imageFlg==0) {
                canvas.drawBitmap(aliveWingsDownImage,posX,posY,null);
                imageFlg2--;
                if(imageFlg2==0)
                    imageFlg=1;
            }
        }
        else
            canvas.drawBitmap(deadImage,posX,posY,null);
        update();
    }

    private void update(){
        if(IsRightDirection)
            this.posX += birdSpeedX;
        else
            this.posX -= birdSpeedX;
        this.posY += birdSpeedY;
    }
}
