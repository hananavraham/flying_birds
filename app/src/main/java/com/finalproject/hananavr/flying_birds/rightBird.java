package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class rightBird {
    private float posX;
    private float posY;
    private int imageWidth;
    private int imageHeight;
    private int birdSpeed;
    private Bitmap image;

    public rightBird(Context context, Bitmap bitmap) {
        this.image = bitmap;
        imageWidth = bitmap.getWidth();
        imageHeight = bitmap.getHeight();
    }

    public void setBirdInfo(float x, float y, int speed){
        this.posY = y;
        this.posX = x;
        this.birdSpeed = speed;
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

    public void draw(Canvas canvas){
        canvas.drawBitmap(image,posX,posY,null);
        update();
    }

    private void update(){
        this.posX -= birdSpeed;
    }
}
