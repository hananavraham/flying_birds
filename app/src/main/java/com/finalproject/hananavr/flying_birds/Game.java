package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.ListIterator;

public class Game extends View {

    ArrayList<Bird> birds = new ArrayList<>();
    Paint paint;
    InGameMenu igm;
    Bitmap heart, inGameMenu, rightShooter, leftShooter;
    int pause_flg, lives, fontSize, shooterDirection_flg, score;


    public Game(Context context) {
        super(context);
        lives = 5;
        pause_flg = 0;
        shooterDirection_flg = 0;
        score = 0;
        //Random r = new Random();

        //Creating different type of birds
        GreenBird leftGreen = new GreenBird(context,true);
        leftGreen.setBirdInfo(-260,0, 5);

        birds.add(leftGreen);

        BrownBird rightBrown = new BrownBird(context,false);
        rightBrown.setBirdInfo(Resources.getSystem().getDisplayMetrics().widthPixels,400, 5);

        birds.add(rightBrown);

        YellowBird leftYellow = new YellowBird(context,true);
        leftYellow.setBirdInfo(-260,400,5);
        birds.add(leftYellow);

        BarcaBird rightBarca = new BarcaBird(context,false);
        rightBarca.setBirdInfo(Resources.getSystem().getDisplayMetrics().widthPixels,180,8);
        birds.add(rightBarca);

        Eagle leftEagle = new Eagle(context, true);
        leftEagle.setBirdInfo(-260, 240, 6);
        birds.add(leftEagle);


        //Setting for font style (used for showing the live left as text)
        fontSize = getResources().getDimensionPixelSize(R.dimen.myFontSize);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(fontSize);

        //All the necessery resources for in game menu
        inGameMenu = BitmapFactory.decodeResource(getResources(), R.drawable.menu_button);
        Bitmap igmBackground = BitmapFactory.decodeResource(getResources(), R.drawable.igmbackground);
        Bitmap igmResume = BitmapFactory.decodeResource(getResources(), R.drawable.resumegameoption);
        Bitmap igmMainMenu = BitmapFactory.decodeResource(getResources(), R.drawable.mainmenuoption);
        Bitmap igmExit = BitmapFactory.decodeResource(getResources(), R.drawable.exitgameoption);
        igm = new InGameMenu(context,igmBackground,igmResume,igmMainMenu,igmExit);

        //Heart image to show next to the lives left text
        heart = BitmapFactory.decodeResource(getResources(), R.drawable.heart);

        //Pictures of left & right shooter direction for changing shooter direction animation
        rightShooter = BitmapFactory.decodeResource(getResources(), R.drawable.rightshooter);
        leftShooter = BitmapFactory.decodeResource(getResources(), R.drawable.leftshooter);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        ListIterator it = birds.listIterator();
        while (it.hasNext()){
            Bird bird = (Bird) it.next();
            bird.draw(canvas);
        }
        //Drawing lives left as text
        canvas.drawText(String.valueOf(lives),10,fontSize, paint);

        //Drawing heart image
        canvas.drawBitmap(heart,35,4,null);

        //Drawing current score as text
        canvas.drawText("Score: "+String.valueOf(score),95,fontSize, paint);

        //Drawing in game menu image
        canvas.drawBitmap(inGameMenu,Resources.getSystem().getDisplayMetrics().widthPixels-100,4,null);

        checkBirdPassingScreen();

        if(shooterDirection_flg == 0)
            canvas.drawBitmap(rightShooter,Resources.getSystem().getDisplayMetrics().widthPixels/2-50,240,null);
        else
            canvas.drawBitmap(leftShooter,Resources.getSystem().getDisplayMetrics().widthPixels/2-100,235,null);

        if(pause_flg == 1){
            igm.draw(canvas);
        }
        invalidate();
    }

    private void checkBirdPassingScreen() {
        ListIterator it = birds.listIterator();
        while (it.hasNext()){
            Bird bird = (Bird) it.next();
            if (bird.IsRightDirection()){
                if(bird.getX() > Resources.getSystem().getDisplayMetrics().widthPixels && !bird.IsDead()){
                lives--;
                birds.remove(bird);
                return;
                }
            }

            else{
                if(bird.getX()+bird.getImageWidth() < 0 && !bird.IsDead()) {
                    lives--;
                    birds.remove(bird);
                    return;
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();

            //Check where the finger pressed to change shooter direction accordingly
            if(x <= Resources.getSystem().getDisplayMetrics().widthPixels/2)
                shooterDirection_flg = 1;
            else
                shooterDirection_flg = 0;

            if(pause_flg == 0){
//                ListIterator it = birds.listIterator();
//                while (it.hasNext()){
//                    Bird bird = (Bird) it.next();
//                    if(!bird.IsDead()){
//                        if (bird.IsRightDirection()){
//                            if(x >= bird.getX() && x < (bird.getX()+bird.getImageWidth()) && y >= bird.getY() && y < (bird.getY()+bird.getImageHeight()))
//                            {
//                                //bird.setImages(deadbird1);
//                                bird.setBirdSpeedY(10);
//                                bird.setDead(true);
//                                return true;
//                            }
//                        }
//                        else{
//                            if(x >= bird.getX() && x < (bird.getX()+bird.getImageWidth()) && y >= bird.getY() && y < (bird.getY()+bird.getImageHeight()))
//                            {
//                                //bird.setImages(deadbird3);
//                                bird.setBirdSpeedY(10);
//                                bird.setDead(true);
//                                return true;
//                            }
//                        }
//                    }
//                }
                //Check for every bird if the clicks count are enough to turn the bird dead
                ListIterator it = birds.listIterator();
                while (it.hasNext()){
                    Bird bird = (Bird) it.next();
                    if(!bird.IsDead()){
                        if(x >= bird.getX() && x < (bird.getX()+bird.getImageWidth()) && y >= bird.getY() && y < (bird.getY()+bird.getImageHeight()))
                        {
                            bird.currentClicksCounter++;
                            if(bird.requiredClicksToKill == bird.currentClicksCounter){
                                score += bird.score;
                                bird.setBirdSpeedY(10);
                                bird.setDead(true);
                                return true;
                            }
                        }
                    }
                }

                //Check if in game menu icon is pressed
                if(x >= Resources.getSystem().getDisplayMetrics().widthPixels-100 && x < (Resources.getSystem().getDisplayMetrics().widthPixels-100+inGameMenu.getWidth()) && y >= 4 && y < (4+inGameMenu.getHeight())){
                    pause_flg = 1;
                    Thread pausedStateThread = new Thread(){
                        public void run(){
                            ListIterator it = birds.listIterator();
                            while(it.hasNext()){
                                Bird bird = (Bird) it.next();
                                bird.pausedState();
                            }
                        }
                    };
                    pausedStateThread.start();
                    return true;
                }
            }

            //If the in game menu is open, only checks this code section
            if(pause_flg == 1){
                if(x >= 810 && x < (810+igm.getOptionWidth()) && y >= 265 && y < (265+igm.getOptionHeight()))
                {
                    Thread runningStateThread = new Thread(){
                        public void run(){
                            ListIterator it = birds.listIterator();
                            while(it.hasNext()){
                                Bird bird = (Bird) it.next();
                                bird.runningState();
                            }
                        }
                    };
                    runningStateThread.start();
                    pause_flg = 0;
                }
                if(x >= 810 && x < (810+igm.getOptionWidth()) && y >= 350 && y < (350+igm.getOptionHeight())){
                    Thread backToMainMenuThread = new Thread(){
                        public void run(){
                            getContext().startActivity(new Intent(getContext(),MainActivity.class));
                        }
                    };
                    backToMainMenuThread.start();
                    return true;
                }
                if(x >= 810 && x < (810+igm.getOptionWidth()) && y >= 435 && y < (435+igm.getOptionHeight())){
                    Thread exitGameThread = new Thread(){
                        public void run(){
                            System.exit(0);
                        }
                    };
                    exitGameThread.start();
                    return true;
                }
            }
        }
        return false;
    }
}


//                Toast.makeText(this.getContext().getApplicationContext(), "touch:" +  String.valueOf(x) + "|" + String.valueOf(y) + "bird: " + String.valueOf(bir.getX() + "|" + String.valueOf(y)) ,Toast.LENGTH_LONG).show();