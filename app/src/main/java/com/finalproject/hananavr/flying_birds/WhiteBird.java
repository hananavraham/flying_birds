package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.BitmapFactory;

public class WhiteBird extends Bird{

    public WhiteBird(Context context, boolean RightDirection, int requiredToKill) {
        super(context, RightDirection);

        if(RightDirection){
            super.aliveWingsUpImage = BitmapFactory.decodeResource(getResources(), R.drawable.whitebird_lr_frame_1);
            super.aliveWingsDownImage = BitmapFactory.decodeResource(getResources(), R.drawable.whitebird_lr_frame_2);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.deadwhitebird_lr_frame_1);
        }else{
            super.aliveWingsUpImage = BitmapFactory.decodeResource(getResources(), R.drawable.whitebird_rl_frame_1);
            super.aliveWingsDownImage = BitmapFactory.decodeResource(getResources(), R.drawable.whitebird_rl_frame_2);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.deadwhitebird_rl_frame_1);
        }

        super.score = 1;
        super.requiredClicksToKill = requiredToKill;
    }
}
