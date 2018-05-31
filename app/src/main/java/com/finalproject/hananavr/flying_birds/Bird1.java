package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.TextureView;
import android.view.View;

import java.util.Vector;

public class Bird1{
    private float posX;
    private float posY;
    private Bitmap image;

    public Bird1(Context context, Bitmap bitmap) {
        this.image = bitmap;
    }

    public void setCoord(float x, float y){
        this.posY = y;
        this.posX = x;
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
        this.posX += 5;
        //this.poxY += 5;
    }

}
