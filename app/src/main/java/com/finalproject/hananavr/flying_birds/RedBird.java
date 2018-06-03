package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class RedBird extends Bird{

    private int score;
    private int ShootToKill;

    public RedBird(Context context, boolean RightDirection) {
        super(context, RightDirection);
        score = 10;
        ShootToKill = 1;
    }

    public int getScore() { return score; }

    public int getShootToKill() { return ShootToKill; }

    public void updateShootToKill(int shootToKill) {
        ShootToKill = shootToKill;
    }
}
