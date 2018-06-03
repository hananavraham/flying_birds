package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.BitmapFactory;

public class BrownBird extends Bird {

    public BrownBird(Context context, boolean RightDirection) {
        super(context, RightDirection);

        if(RightDirection){
            super.aliveImage = BitmapFactory.decodeResource(getResources(), R.drawable.brown_bird_right_dir);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.brown_dead_bird_right_dir);
        }else{
            super.aliveImage = BitmapFactory.decodeResource(getResources(), R.drawable.brown_bird_left_dir);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.brown_dead_bird_left_dir);
        }

        super.score = 40;
        super.requiredClicksToKill = 3;
    }

}
