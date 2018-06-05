package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.BitmapFactory;

public class Eagle extends Bird {

    public Eagle(Context context, boolean RightDirection) {
        super(context, RightDirection);

        if(RightDirection){
            super.aliveWingsUpImage = BitmapFactory.decodeResource(getResources(), R.drawable.eagle_lr_frame_1);
            super.aliveWingsDownImage = BitmapFactory.decodeResource(getResources(), R.drawable.eagle_lr_frame_2);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.deadeagle_lr_frame_1);
        }else{
            super.aliveWingsUpImage = BitmapFactory.decodeResource(getResources(), R.drawable.eagle_rl_frame_1);
            super.aliveWingsDownImage = BitmapFactory.decodeResource(getResources(), R.drawable.eagle_rl_frame_2);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.deadeagle_rl_frame_1);
        }

        super.score = 200;
        super.requiredClicksToKill = 5;
    }
}
