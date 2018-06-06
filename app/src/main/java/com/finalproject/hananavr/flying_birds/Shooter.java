package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;
import android.widget.Toast;

public class Shooter extends View {
    int xPos;
    int yPos;
    int draw_flg;
    int motion_flg;
    float oldTouch;
    Bitmap[] shooterRightMotion;
    Bitmap[] shooterLeftMotion;

    public Shooter(Context context, int x, int y) {
        super(context);
        this.xPos = x;
        this.yPos = y;
        draw_flg = 0;
        oldTouch = 0;
        motion_flg = 0;
        shooterRightMotion = new Bitmap[5];
        shooterLeftMotion = new Bitmap[5];
        initRightImages();
        initLeftImages();
    }

    private void initLeftImages() {
        shooterLeftMotion[0] = BitmapFactory.decodeResource(getResources(), R.drawable.shooter_left_1);
        shooterLeftMotion[1] = BitmapFactory.decodeResource(getResources(), R.drawable.shooter_left_2);
        shooterLeftMotion[2] = BitmapFactory.decodeResource(getResources(), R.drawable.shooter_left_3);
        shooterLeftMotion[3] = BitmapFactory.decodeResource(getResources(), R.drawable.shooter_left_4);
        shooterLeftMotion[4] = BitmapFactory.decodeResource(getResources(), R.drawable.shooter_left_5);
    }

    private void initRightImages() {
        shooterRightMotion[0] = BitmapFactory.decodeResource(getResources(), R.drawable.shooter_right_1);
        shooterRightMotion[1] = BitmapFactory.decodeResource(getResources(), R.drawable.shooter_right_2);
        shooterRightMotion[2] = BitmapFactory.decodeResource(getResources(), R.drawable.shooter_right_3);
        shooterRightMotion[3] = BitmapFactory.decodeResource(getResources(), R.drawable.shooter_right_4);
        shooterRightMotion[4] = BitmapFactory.decodeResource(getResources(), R.drawable.shooter_right_5);
    }

    public void draw(Canvas canvas, float xTouchPos) {
        super.draw(canvas);
            if(xTouchPos <= Resources.getSystem().getDisplayMetrics().widthPixels / 2){
                if(draw_flg>=0 && draw_flg<10 ){
                    canvas.drawBitmap(shooterLeftMotion[0], this.xPos, this.yPos, null);
                    //Toast.makeText(this.getContext().getApplicationContext(), "draw flg is: "+String.valueOf(draw_flg) ,Toast.LENGTH_LONG).show();
                    draw_flg++;
                }
                else if(draw_flg>=10 && draw_flg<20 ){
                    canvas.drawBitmap(shooterLeftMotion[1], this.xPos, this.yPos, null);
                    //Toast.makeText(this.getContext().getApplicationContext(), "2" ,Toast.LENGTH_LONG).show();
                    draw_flg++;
                }
                else if(draw_flg>=20 && draw_flg<30 ){
                    canvas.drawBitmap(shooterLeftMotion[2], this.xPos, this.yPos, null);
                    //Toast.makeText(this.getContext().getApplicationContext(), "3" ,Toast.LENGTH_LONG).show();
                    draw_flg++;
                }
                else if(draw_flg>=30 && draw_flg<40 ){
                    canvas.drawBitmap(shooterLeftMotion[3], this.xPos, this.yPos, null);
                    //Toast.makeText(this.getContext().getApplicationContext(), "4" ,Toast.LENGTH_LONG).show();
                    draw_flg++;
                }
                else if(draw_flg>=40 && draw_flg<=50 ){
                    canvas.drawBitmap(shooterLeftMotion[4], this.xPos, this.yPos, null);
                    //Toast.makeText(this.getContext().getApplicationContext(), "5" ,Toast.LENGTH_LONG).show();
                    draw_flg++;
                    if(draw_flg >= 50)
                        draw_flg = 0;
                }
            }else{
                if(draw_flg>=0 && draw_flg<10 ){
                    canvas.drawBitmap(shooterRightMotion[0], this.xPos, this.yPos, null);
                    //Toast.makeText(this.getContext().getApplicationContext(), "draw flg is: "+String.valueOf(draw_flg) ,Toast.LENGTH_LONG).show();
                    draw_flg++;
                }
                else if(draw_flg>=10 && draw_flg<20 ){
                    canvas.drawBitmap(shooterRightMotion[1], this.xPos, this.yPos, null);
                    //Toast.makeText(this.getContext().getApplicationContext(), "2" ,Toast.LENGTH_LONG).show();
                    draw_flg++;
                }
                else if(draw_flg>=20 && draw_flg<30 ){
                    canvas.drawBitmap(shooterRightMotion[2], this.xPos, this.yPos, null);
                    //Toast.makeText(this.getContext().getApplicationContext(), "3" ,Toast.LENGTH_LONG).show();
                    draw_flg++;
                }
                else if(draw_flg>=30 && draw_flg<40 ){
                    canvas.drawBitmap(shooterRightMotion[3], this.xPos, this.yPos, null);
                    //Toast.makeText(this.getContext().getApplicationContext(), "4" ,Toast.LENGTH_LONG).show();
                    draw_flg++;
                }
                else if(draw_flg>=40 && draw_flg<=50 ){
                    canvas.drawBitmap(shooterRightMotion[4], this.xPos, this.yPos, null);
                    //Toast.makeText(this.getContext().getApplicationContext(), "5" ,Toast.LENGTH_LONG).show();
                    draw_flg++;
                    if(draw_flg >= 50)
                        draw_flg = 0;
                }
            }
    }
}

