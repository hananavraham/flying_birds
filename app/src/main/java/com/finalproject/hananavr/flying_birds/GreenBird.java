package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.BitmapFactory;

public class GreenBird extends Bird{

    public GreenBird(Context context, boolean RightDirection) {
        super(context, RightDirection);

        if(RightDirection){
            super.aliveImage = BitmapFactory.decodeResource(getResources(), R.drawable.green_bird_right_dir);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.green_dead_bird_right_dir);
        }else{
            super.aliveImage = BitmapFactory.decodeResource(getResources(), R.drawable.green_bird_left_dir);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.green_dead_bird_left_dir);
        }

        super.score = 10;
        super.requiredClicksToKill = 1;
    }

}
