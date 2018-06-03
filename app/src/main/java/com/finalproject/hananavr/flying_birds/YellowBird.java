package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.Bitmap;

public class YellowBird extends Bird {

    private int score;
    private int ShootToKill;

    public YellowBird(Context context, boolean RightDirection) {
        super(context, RightDirection);
        score = 20;
        ShootToKill = 2;
    }

    public int getScore() { return score; }

    public int getShootToKill() { return ShootToKill; }

    public void updateShootToKill(int shootToKill) {
        ShootToKill = shootToKill;
    }
}
