package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.BitmapFactory;

public class GreyBird extends Bird {

    public GreyBird(Context context, boolean RightDirection) {
        super(context, RightDirection);

        if(RightDirection){
            super.aliveWingsUpImage = BitmapFactory.decodeResource(getResources(), R.drawable.grey_bird_lr_frame_1);
            super.aliveWingsDownImage = BitmapFactory.decodeResource(getResources(), R.drawable.grey_bird_lr_frame_2);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.deadgrey_bird_lr_frame_1);
        }else{
            super.aliveWingsUpImage = BitmapFactory.decodeResource(getResources(), R.drawable.grey_bird_rl_frame_1);
            super.aliveWingsDownImage = BitmapFactory.decodeResource(getResources(), R.drawable.grey_bird_rl_frame_2);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.deadgrey_bird_rl_frame_1);
        }

        super.score = 40;
        super.requiredClicksToKill = 3;
    }

}
