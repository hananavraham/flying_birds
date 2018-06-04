package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.BitmapFactory;

public class Eagle extends Bird {

    public Eagle(Context context, boolean RightDirection) {
        super(context, RightDirection);

        if(RightDirection){
            super.aliveImage = BitmapFactory.decodeResource(getResources(), R.drawable.eagle_right_dir);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.eagle_dead_right_dir);
        }else{
            super.aliveImage = BitmapFactory.decodeResource(getResources(), R.drawable.eagle_left_dir);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.eagle_dead_left_dir);
        }

        super.score = 200;
        super.requiredClicksToKill = 5;
    }
}
