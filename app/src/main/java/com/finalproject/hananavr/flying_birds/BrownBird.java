package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.Bitmap;

public class BrownBird extends Bird {

    private int score;
    private int ShootToKill;

    public BrownBird(Context context, boolean RightDirection) {
        super(context, RightDirection);
        score = 40;
        ShootToKill = 3;
    }

    public int getScore() { return score; }

    public int getShootToKill() { return ShootToKill; }

    public void updateShootToKill(int shootToKill) {
        ShootToKill = shootToKill;
    }
}
