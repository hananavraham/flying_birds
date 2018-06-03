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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import static java.lang.System.exit;

public class Game extends View {

    ArrayList<Bird> birds = new ArrayList<>();
    ArrayList<leftBird> leftBirds = new ArrayList<>();
    ArrayList<rightBird> rightBirds = new ArrayList<>();
    Paint paint;
    InGameMenu igm;
    Bitmap heart, inGameMenu, rightShooter, leftShooter, deadbird1, deadbird3;
    int pause_flg, lives, fontSize, shooterDirection_flg;


    public Game(Context context) {
        super(context);
        lives = 5;
        pause_flg = 0;
        shooterDirection_flg = 0;
        //Random r = new Random();
//        Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.bird1_1);
//        leftBird bird = new leftBird(context, b1);
//        bird.setBirdInfo(-260, 0, 5);
//        leftBirds.add(bird);
//
//        leftBird leftBird = new leftBird(context,b1);
//        leftBird.setBirdInfo(-260,500, 10);
//        leftBirds.add(leftBird);
//
//        Bitmap b2 = BitmapFactory.decodeResource(getResources(), R.drawable.rightbird3);
//        rightBird birdd = new rightBird(context,b2);
//        birdd.setBirdInfo(Resources.getSystem().getDisplayMetrics().widthPixels,0, 8);
//        rightBirds.add(birdd);

        RedBird leftRed = new RedBird(context,true);
        leftRed.setImages(BitmapFactory.decodeResource(getResources(), R.drawable.redbirdleft),BitmapFactory.decodeResource(getResources(), R.drawable.deadredbirdleft));
        leftRed.setBirdInfo(-260,0, 5);

        birds.add(leftRed);

        leftRed = new RedBird(context,false);
        leftRed.setImages(BitmapFactory.decodeResource(getResources(), R.drawable.redbirdleft),BitmapFactory.decodeResource(getResources(), R.drawable.deadredbirdleft));
        leftRed.setBirdInfo(Resources.getSystem().getDisplayMetrics().widthPixels,400, 5);

        birds.add(leftRed);

        YellowBird rightYellow = new YellowBird(context,true);
        rightYellow.setImages(BitmapFactory.decodeResource(getResources(), R.drawable.yellowbirdright),BitmapFactory.decodeResource(getResources(), R.drawable.deadyellowbirdright));
        rightYellow.setBirdInfo(-260,400,5);
        birds.add(rightYellow);

        
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

        //Pictures of dead birds for dead bird animation
        deadbird1 = BitmapFactory.decodeResource(getResources(), R.drawable.deadbird1);
        deadbird3 = BitmapFactory.decodeResource(getResources(), R.drawable.deadbird3);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        Iterator it = leftBirds.iterator();
//        while (it.hasNext()) {
//            leftBird bir = (leftBird) it.next();
//            bir.draw(canvas);
//            if(bir.isDead() && bir.getY() > Resources.getSystem().getDisplayMetrics().heightPixels)
//                leftBirds.remove(bir);
//        }
//
//        ListIterator it2 = rightBirds.listIterator();
//        while (it2.hasNext()){
//            rightBird bird = (rightBird) it2.next();
//            bird.draw(canvas);
//            if(bird.isDead() && bird.getY() > Resources.getSystem().getDisplayMetrics().heightPixels)
//                rightBirds.remove(bird);
//        }

        ListIterator it = birds.listIterator();
        while (it.hasNext()){
            Bird bird = (Bird) it.next();
            bird.draw(canvas);
        }
        //Drawing lives left as text
        canvas.drawText(String.valueOf(lives),10,fontSize, paint);

        //Drawing heart image
        canvas.drawBitmap(heart,35,4,null);

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
//        ListIterator it = leftBirds.listIterator();
//        while (it.hasNext()) {
//            leftBird bir = (leftBird) it.next();
//            if(bir.getX() > Resources.getSystem().getDisplayMetrics().widthPixels && !bir.isDead()){
//                lives--;
//                leftBirds.remove(bir);
//                return;
//            }
//        }
//
//        ListIterator it2 = rightBirds.listIterator();
//        while (it2.hasNext()){
//            rightBird bird = (rightBird) it2.next();
//            if(bird.getX()+bird.getImageWidth() < 0 && !bird.isDead())
//            {
//                lives--;
//                rightBirds.remove(bird);
//                return;
//            }
//        }

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

            if(x <= Resources.getSystem().getDisplayMetrics().widthPixels/2)
                shooterDirection_flg = 1;
            else
                shooterDirection_flg = 0;

            if(pause_flg == 0){
                //Move through all RIGHT birds and check if the user clicked on any of the RIGHT birds or not
//                ListIterator it2 = rightBirds.listIterator();
//                while (it2.hasNext()) {
//                    rightBird bir2 = (rightBird) it2.next();
//                    if (!bir2.isDead()) {
//                        if(x >= bir2.getX() && x < (bir2.getX()+bir2.getImageWidth()) && y >= bir2.getY() && y < (bir2.getY()+bir2.getImageHeight()))
//                        {
//                            bir2.setImage(deadbird3);
//                            bir2.setBirdSpeedY(10);
//                            bir2.setDead(true);
//                            return true;
//                        }
//                    }
//                }
//
//                //Move through all LEFT birds and check if the user clicked on any of the LEFT birds or not
//                ListIterator it = leftBirds.listIterator();
//                while (it.hasNext()) {
//                    leftBird bir = (leftBird) it.next();
//                    if (!bir.isDead()) {
//                        if(x >= bir.getX() && x < (bir.getX()+bir.getImageWidth()) && y >= bir.getY() && y < (bir.getY()+bir.getImageHeight()))
//                        {
//                            bir.setImage(deadbird1);
//                            bir.setBirdSpeedY(10);
//                            bir.setDead(true);
//                            return true;
//                        }
//                    }
//                }

                ListIterator it = birds.listIterator();
                while (it.hasNext()){
                    Bird bird = (Bird) it.next();
                    if(!bird.IsDead()){
                        if (bird.IsRightDirection()){
                            if(x >= bird.getX() && x < (bird.getX()+bird.getImageWidth()) && y >= bird.getY() && y < (bird.getY()+bird.getImageHeight()))
                            {
                                //bird.setImages(deadbird1);
                                bird.setBirdSpeedY(10);
                                bird.setDead(true);
                                return true;
                            }
                        }

                        else{
                            if(x >= bird.getX() && x < (bird.getX()+bird.getImageWidth()) && y >= bird.getY() && y < (bird.getY()+bird.getImageHeight()))
                            {
                                //bird.setImages(deadbird3);
                                bird.setBirdSpeedY(10);
                                bird.setDead(true);
                                return true;
                            }
                        }
                    }
                }

                if(x >= Resources.getSystem().getDisplayMetrics().widthPixels-100 && x < (Resources.getSystem().getDisplayMetrics().widthPixels-100+inGameMenu.getWidth()) && y >= 4 && y < (4+inGameMenu.getHeight())){
                    pause_flg = 1;
                    Thread pausedStateThread = new Thread(){
                        public void run(){
//                            ListIterator it3 = leftBirds.listIterator();
////                            while (it3.hasNext()) {
////                                leftBird bir = (leftBird) it3.next();
////                                bir.pausedState();
////                            }
////                            ListIterator it4 = rightBirds.listIterator();
////                            while (it4.hasNext()) {
////                                rightBird bir2 = (rightBird) it4.next();
////                                bir2.pausedState();
////                            }
                            ListIterator it = birds.listIterator();
                            while(it.hasNext()){
                                Bird bird = (Bird) it.next();
                                bird.pausedState();;
                            }
                        }
                    };
                    pausedStateThread.start();
                    return true;
                }
            }

            if(pause_flg == 1){
                if(x >= 810 && x < (810+igm.getOptionWidth()) && y >= 265 && y < (265+igm.getOptionHeight()))
                {
                    Thread runningStateThread = new Thread(){
                        public void run(){
//                            ListIterator it = leftBirds.listIterator();
//                            while (it.hasNext()) {
//                                leftBird bir = (leftBird) it.next();
//                                bir.runningState();
//                            }
//
//                            ListIterator it2 = rightBirds.listIterator();
//                            while (it2.hasNext()){
//                                rightBird bird = (rightBird) it2.next();
//                                bird.runningState();
//                            }
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