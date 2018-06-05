package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.BitmapFactory;

public class YellowBird extends Bird {

    public YellowBird(Context context, boolean RightDirection) {
        super(context, RightDirection);

        if(RightDirection){
            super.aliveWingsUpImage = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_lr_frame_1);
            super.aliveWingsDownImage = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_lr_frame_2);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_lr_deadframe_1);
        }else{
            super.aliveWingsUpImage = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_rl_frame_1);
            super.aliveWingsDownImage = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_rl_frame_2);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_rl_deadframe_1);
        }

        super.score = 20;
        super.requiredClicksToKill = 2;
    }

}
