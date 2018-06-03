package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.BitmapFactory;

public class YellowBird extends Bird {

    public YellowBird(Context context, boolean RightDirection) {
        super(context, RightDirection);

        if(RightDirection){
            super.aliveImage = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_bird_right_dir);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_dead_bird_right_dir);
        }else{
            super.aliveImage = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_bird_left_dir);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_dead_bird_left_dir);
        }

        super.score = 20;
        super.requiredClicksToKill = 2;
    }

}
