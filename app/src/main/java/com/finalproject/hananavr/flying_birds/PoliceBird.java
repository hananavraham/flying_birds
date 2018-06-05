package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.graphics.BitmapFactory;

public class PoliceBird extends Bird {


    public PoliceBird(Context context, boolean RightDirection) {
        super(context, RightDirection);

        if(RightDirection){
            super.aliveWingsUpImage = BitmapFactory.decodeResource(getResources(), R.drawable.police_lr_frame_1);
            super.aliveWingsDownImage = BitmapFactory.decodeResource(getResources(), R.drawable.police_lr_frame_2);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.deadpolice_lr_frame_1);
        }else{
            super.aliveWingsUpImage = BitmapFactory.decodeResource(getResources(), R.drawable.police_rl_frame_1);
            super.aliveWingsDownImage = BitmapFactory.decodeResource(getResources(), R.drawable.police_rl_frame_2);
            super.deadImage = BitmapFactory.decodeResource(getResources(), R.drawable.deadpolice_rl_frame_1);
        }

        super.score = 80;
        super.requiredClicksToKill = 4;
    }
}
