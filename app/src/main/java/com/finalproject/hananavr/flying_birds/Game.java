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
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game extends View {

    protected ArrayList<Bird> birds = new ArrayList<>();
    private Random r;
    private Paint paint;
    private InGameMenu igm;
    private Bitmap heart, inGameMenu;
    private Shooter shooter;
    private int lives, fontSize, score, difficultyChanger, difficultyChangerFlg, whiteBirdClicksToKill, currListSize;
    protected int pause_flg, soundsToogle;
    private float xTouchPos;

    private SurvivalTimer st;

    //Variables needed for proper random bird creation logic
    protected Runnable runnable;
    protected ScheduledExecutorService service;
    protected Future<?> future;

    /**
     * Game class constructor.
     * Initialize all the needed variables and functions for a proper game flow.
     * @param context
     */
    public Game(Context context) {
        super(context);
        st = new SurvivalTimer();
        st.startSurvivalTimer();
        //Needed variables for game flow
        r = new Random();
        lives = 5;
        pause_flg = 0;
        score = 0;
        difficultyChanger = 4;
        difficultyChangerFlg = 1;
        xTouchPos = 0;
        soundsToogle = 0;
        whiteBirdClicksToKill = 8;

        shooter = new Shooter(context, Resources.getSystem().getDisplayMetrics().widthPixels/2-90, 440);

        //Setting in game fonts style
        fontSize = getResources().getDimensionPixelSize(R.dimen.inGameFontSize);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(fontSize);

        //All the necessery resources for in game menu
        inGameMenu = BitmapFactory.decodeResource(getResources(), R.drawable.menu_button);
        Bitmap igmBackground = BitmapFactory.decodeResource(getResources(), R.drawable.igmbackground);
        Bitmap igmResume = BitmapFactory.decodeResource(getResources(), R.drawable.resumeoption);
        Bitmap igmRestart = BitmapFactory.decodeResource(getResources(), R.drawable.restartoption);
        Bitmap igmMainMenu = BitmapFactory.decodeResource(getResources(), R.drawable.mainmenuoption);
        Bitmap soundsOn = BitmapFactory.decodeResource(getResources(), R.drawable.soundson);
        Bitmap soundsOff = BitmapFactory.decodeResource(getResources(), R.drawable.soundsoff);
        igm = new InGameMenu(context,igmBackground,igmResume,igmRestart,igmMainMenu, soundsOn, soundsOff);

        //Heart image to show next to the lives left text
        heart = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        startGame();
    }

    /**
     * Creates a bird every 3 seconds with random values and suitable speed according to the current score.
     */
    private void startGame() {
        runnable = new Runnable() {
            public void run() {
                boolean rndBool;
                Random r = new Random();
                if(score > 1000 && difficultyChangerFlg == 1){
                    difficultyChanger += 2;
                    difficultyChangerFlg++;
                } else if(score > 2000 && difficultyChangerFlg == 2){
                    difficultyChanger += 2;
                    difficultyChangerFlg++;
                }else if(score > 3000 && difficultyChangerFlg == 3){
                    difficultyChanger += 2;
                    difficultyChangerFlg++;
                    whiteBirdClicksToKill = 6;
                }else if(score > 4000 && difficultyChangerFlg == 4){
                    difficultyChanger += 2;
                    difficultyChangerFlg++;
                }else if(score > 5000 && difficultyChangerFlg == 5){
                    difficultyChanger += 2;
                    difficultyChangerFlg++;
                    whiteBirdClicksToKill = 4;
                }else if(score > 6000 && difficultyChangerFlg == 6){
                    difficultyChanger += 2;
                    difficultyChangerFlg++;
                }else if(score > 7000 && difficultyChangerFlg == 7){
                    difficultyChanger += 2;
                    difficultyChangerFlg++;
                    whiteBirdClicksToKill = 2;
                }else if(score > 8000 && difficultyChangerFlg == 8){
                    difficultyChanger += 2;
                    difficultyChangerFlg++;
                }else if(score > 9000 && difficultyChangerFlg == 9){
                    difficultyChanger += 2;
                    difficultyChangerFlg++;
                }else if(score > 10000 && difficultyChangerFlg == 10){
                    difficultyChanger += 2;
                    difficultyChangerFlg++;
                    whiteBirdClicksToKill = 1;
                }

                int rndBirdNum = r.nextInt(55);
                if(rndBirdNum >= 0 && rndBirdNum < 10){
                    rndBool = r.nextBoolean();
                    if(rndBool){
                        RedBird leftRed = new RedBird(getContext(),rndBool);
                        leftRed.setBirdInfo(-260,r.nextInt(600), difficultyChanger);
                        birds.add(leftRed);
                    }else{
                        RedBird rightRed = new RedBird(getContext(),rndBool);
                        rightRed.setBirdInfo(Resources.getSystem().getDisplayMetrics().widthPixels,r.nextInt(600), difficultyChanger);
                        birds.add(rightRed);
                    }
                }else if(rndBirdNum >= 10 && rndBirdNum < 20){
                    rndBool = r.nextBoolean();
                    if(rndBool){
                        YellowBird leftYellow = new YellowBird(getContext(),rndBool);
                        leftYellow.setBirdInfo(-260,r.nextInt(600),difficultyChanger);
                        birds.add(leftYellow);
                    }else{
                        YellowBird rightYellow = new YellowBird(getContext(),rndBool);
                        rightYellow.setBirdInfo(Resources.getSystem().getDisplayMetrics().widthPixels,r.nextInt(600),difficultyChanger);
                        birds.add(rightYellow);
                    }
                }else if(rndBirdNum >= 20 && rndBirdNum < 30){
                    rndBool = r.nextBoolean();
                    if(rndBool){
                        GreyBird leftGrey = new GreyBird(getContext(),rndBool);
                        leftGrey.setBirdInfo(-260,r.nextInt(600), difficultyChanger);
                        birds.add(leftGrey);
                    }else{
                        GreyBird rightGrey = new GreyBird(getContext(),rndBool);
                        rightGrey.setBirdInfo(Resources.getSystem().getDisplayMetrics().widthPixels,r.nextInt(600), difficultyChanger);
                        birds.add(rightGrey);
                    }
                }else if(rndBirdNum >= 30 && rndBirdNum < 40){
                    rndBool = r.nextBoolean();
                    if(rndBool){
                        PoliceBird leftPolice = new PoliceBird(getContext(),rndBool);
                        leftPolice.setBirdInfo(-260,r.nextInt(600),difficultyChanger);
                        birds.add(leftPolice);
                    }else{
                        PoliceBird rightPolice = new PoliceBird(getContext(),rndBool);
                        rightPolice.setBirdInfo(Resources.getSystem().getDisplayMetrics().widthPixels,r.nextInt(600),difficultyChanger);
                        birds.add(rightPolice);
                    }
                }else if(rndBirdNum >= 40 && rndBirdNum < 50){
                    rndBool = r.nextBoolean();
                    if(rndBool){
                        Eagle leftEagle = new Eagle(getContext(), rndBool);
                        leftEagle.setBirdInfo(-260, r.nextInt(600), difficultyChanger);
                        birds.add(leftEagle);
                    }else{
                        Eagle rightEagle = new Eagle(getContext(), rndBool);
                        rightEagle.setBirdInfo(Resources.getSystem().getDisplayMetrics().widthPixels, r.nextInt(600), difficultyChanger);
                        birds.add(rightEagle);
                    }
                }else if(rndBirdNum >= 50 && rndBirdNum < 55){
                    rndBool = r.nextBoolean();
                    if(rndBool){
                        WhiteBird leftWhite = new WhiteBird(getContext(), rndBool, whiteBirdClicksToKill);
                        leftWhite.setBirdInfo(-260, r.nextInt(600), difficultyChanger);
                        birds.add(leftWhite);
                    }else{
                        WhiteBird rightWhite = new WhiteBird(getContext(), rndBool, whiteBirdClicksToKill);
                        rightWhite.setBirdInfo(Resources.getSystem().getDisplayMetrics().widthPixels, r.nextInt(600), difficultyChanger);
                        birds.add(rightWhite);
                    }
                }
            }
        };
        service = Executors.newSingleThreadScheduledExecutor();
        future = service.scheduleAtFixedRate(runnable, 5, 3, TimeUnit.SECONDS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Cause concurrent modification exception sometimes..
//        ListIterator it = birds.listIterator();
//        while (it.hasNext()){
//            Bird bird = (Bird) it.next();
//            bird.draw(canvas, pause_flg);
//        }

        shooter.draw(canvas, xTouchPos);

        currListSize = birds.size();
        for(int i=0; i<currListSize; i++){
            birds.get(i).draw(canvas, pause_flg);
        }

        //Drawing lives left as text
        canvas.drawText(String.valueOf(lives),10,fontSize, paint);

        //Drawing heart image
        canvas.drawBitmap(heart,65,4,null);

        //Drawing current score as text
        canvas.drawText("Score: "+String.valueOf(score),125,fontSize, paint);

        //Drawing survival timer
        canvas.drawText(String.valueOf(st.getMinutesTenths())+String.valueOf(st.getMinutesOneness())+":"+String.valueOf(st.getSecondsTenths())+String.valueOf(st.getSecondsOneness()),(Resources.getSystem().getDisplayMetrics().widthPixels)/2,fontSize, paint);

        //Drawing in game menu image
        canvas.drawBitmap(inGameMenu,Resources.getSystem().getDisplayMetrics().widthPixels-100,4,null);

        checkBirdPassingScreen();

        if(pause_flg == 1){
            igm.draw(canvas,soundsToogle);
        }
        invalidate();
    }

    /**
     * This function checks whether a bird flew off the screen (On y and x axis)
     */
    private void checkBirdPassingScreen() {
        int i, removeFlg = 0;
        for(i = 0; i < birds.size(); i++){
            //Checks if bird is dead and bird Y position passed the height of the screen
            if(birds.get(i).getY() > Resources.getSystem().getDisplayMetrics().heightPixels && birds.get(i).IsDead()){
                removeFlg++;
                break;
            }
            if (birds.get(i).IsRightDirection()){
                //Check if bird is not dead and bird x position passed the width of the screen
                if(birds.get(i).getX() > Resources.getSystem().getDisplayMetrics().widthPixels && !birds.get(i).IsDead()){
                    if(lives > 0)
                        lives--;
                    if (lives == 0){    // Game Over
                        future.cancel(true);
                        SurvivalTimer.timerFuture.cancel(true);
                        Thread resultThread = new Thread(){
                            public void run(){
                                Intent resultThread = new Intent(getContext(), Result.class);
                                resultThread.putExtra("SCORE",score);
                                resultThread.putExtra("SECONDS_ONENESS",st.getSecondsOneness());
                                resultThread.putExtra("SECONDS_TENTHS",st.getSecondsTenths());
                                resultThread.putExtra("MINUTES_ONENESS",st.getMinutesOneness());
                                resultThread.putExtra("MINUTES_TENTHS",st.getMinutesTenths());
                                getContext().startActivity(resultThread);
                            }
                        };
                        resultThread.start();
                    }
                    removeFlg++;
                    break;
                }
            }
            else{
                //Check if bird is not dead and bird x position + bird width passed the start of the screen
                if(birds.get(i).getX()+birds.get(i).getImageWidth() < 0 && !birds.get(i).IsDead()) {
                    if(lives > 0)
                        lives--;
                    if (lives == 0){    // Game Over
                        future.cancel(true);
                        SurvivalTimer.timerFuture.cancel(true);
                        Thread resultThread = new Thread(){
                            public void run(){
                                Intent resultThread = new Intent(getContext(), Result.class);
                                resultThread.putExtra("SCORE",score);
                                resultThread.putExtra("SECONDS_ONENESS",st.getSecondsOneness());
                                resultThread.putExtra("SECONDS_TENTHS",st.getSecondsTenths());
                                resultThread.putExtra("MINUTES_ONENESS",st.getMinutesOneness());
                                resultThread.putExtra("MINUTES_TENTHS",st.getMinutesTenths());
                                getContext().startActivity(resultThread);
                            }
                        };
                        resultThread.start();
                    }
                    removeFlg++;
                    break;
                }
            }
        }
        if(removeFlg > 0){
            // returning the bird to the Birds List as new bird...
            if (birds.size() > 4 || birds.get(i).score == 1)
                birds.remove(i);
            else{
                birds.get(i).setDead(false);
                birds.get(i).setBirdSpeedY(0);
                birds.get(i).setCurrentClicksCounter(0);
                if (birds.get(i).IsRightDirection())
                    birds.get(i).setBirdInfo(-260,r.nextInt(600), difficultyChanger);
                else
                    birds.get(i).setBirdInfo(Resources.getSystem().getDisplayMetrics().widthPixels,r.nextInt(600), difficultyChanger);
            }
        }
    }

    /**
     * This function checks every screen touch according to the current game state
     * @param event
     * @return true if the touch was handled properly and false if not
     */
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();

            if(pause_flg == 0){
                //Check if in game menu icon is pressed
                if(x >= Resources.getSystem().getDisplayMetrics().widthPixels-100 && x < (Resources.getSystem().getDisplayMetrics().widthPixels-100+inGameMenu.getWidth()) && y >= 4 && y < (4+inGameMenu.getHeight())){
                    pause_flg = 1;
                    future.cancel(true);
                    SurvivalTimer.timerFuture.cancel(true);
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

                if(soundsToogle == 0)
                    NewGame.arrowShoot.start();
                xTouchPos = x;
                //Check for every bird if the clicks count are enough to turn the bird dead
                ListIterator it = birds.listIterator();
                while (it.hasNext()){
                    Bird bird = (Bird) it.next();
                    if(!bird.IsDead()){
                        if(x >= bird.getX() && x < (bird.getX()+bird.getImageWidth()) && y >= bird.getY() && y < (bird.getY()+bird.getImageHeight()))
                        {
                            bird.currentClicksCounter++;
                            if(bird.requiredClicksToKill == bird.currentClicksCounter){
                                if(bird.score != 1)
                                    score += bird.score;
                                else
                                    lives++;
                                if(bird.getBirdSpeedX() < 10){
                                    bird.setBirdSpeedY(10);
                                    bird.setBirdSpeedX(10);
                                }else{
                                    bird.setBirdSpeedY(bird.getBirdSpeedX());
                                }
                                bird.setDead(true);
                                if(soundsToogle == 0)
                                    NewGame.deadBird.start();
                                return true;
                            }
                        }
                    }
                }
            }

            //If the in game menu is open, only checks this code section
            if(pause_flg == 1){
                //If resume option is pressed
                if(x >= 820 && x < (820+igm.getOptionWidth()) && y >= 270 && y < (270+igm.getOptionHeight()))
                {
                    future = service.scheduleAtFixedRate(runnable, 2, 3, TimeUnit.SECONDS);
                    SurvivalTimer.timerFuture = SurvivalTimer.timerService.scheduleAtFixedRate(SurvivalTimer.timerRunnable, 1, 1, TimeUnit.SECONDS);
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
                    NewGame.backpressedFlg = 0;
                }
                //If restart option is pressed
                if(x >= 820 && x < (820+igm.getOptionWidth()) && y >= 370 && y < (370+igm.getOptionHeight())){
                    future.cancel(true);
                    SurvivalTimer.timerFuture.cancel(true);
                    Thread restartGameThread = new Thread(){
                        public void run(){
                            NewGame.inGameBackgroundMusic.stop();
                            getContext().startActivity(new Intent(getContext(),NewGame.class));
                        }
                    };
                    restartGameThread.start();
                    return true;

                }
                //If main menu option is pressed
                if(x >= 820 && x < (820+igm.getOptionWidth()) && y >= 470 && y < (470+igm.getOptionHeight())){
                    future.cancel(true);
                    SurvivalTimer.timerFuture.cancel(true);
                    Thread backToMainMenuThread = new Thread(){
                        public void run(){
                            NewGame.inGameBackgroundMusic.stop();
                            getContext().startActivity(new Intent(getContext(),MainActivity.class));
                        }
                    };
                    backToMainMenuThread.start();
                    return true;
                }
                //If toogle button is pressed
                if(x >= 820 && x < (820+igm.getOptionWidth()) && y >= 575 && y < (575+igm.getOptionHeight())){
                    //soundsToogle = 0 when sounds On or 1 when sounds OFF
                    if(soundsToogle == 0){
                        NewGame.inGameBackgroundMusic.pause();
                        soundsToogle = 1;
                        return true;
                    }
                    if(soundsToogle == 1){
                        NewGame.inGameBackgroundMusic.start();
                        soundsToogle = 0;
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }


}


// Toast.makeText(this.getContext().getApplicationContext(), "Text" ,Toast.LENGTH_LONG).show();