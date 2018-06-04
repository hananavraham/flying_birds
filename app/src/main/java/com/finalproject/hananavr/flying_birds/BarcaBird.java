package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.BitmapFactory;

public class BarcaBird extends Bird {

    public BarcaBird(Context context, boolean RightDirection) {
        super(context, RightDirection);

        if(RightDirection){
            super.aliveImage = BitmapFactory.decodeResource(getResources(), R.drawable.barca_bird_right_dir);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.barca_dead_bird_right_dir);
        }else{
            super.aliveImage = BitmapFactory.decodeResource(getResources(), R.drawable.barca_bird_left_dir);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.barca_dead_bird_left_dir);
        }

        super.score = 80;
        super.requiredClicksToKill = 4;
    }
}
