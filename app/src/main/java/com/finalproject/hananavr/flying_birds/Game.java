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

    ArrayList<leftBird> leftBirds = new ArrayList<>();
    ArrayList<rightBird> rightBirds = new ArrayList<>();
    Paint paint;
    Bitmap heart;
    Bitmap inGameMenu;
    Bitmap rightShooter;
    Bitmap leftShooter;
    int pause_flg;
    int shooterDirection_flg = 0;
    int lives;
    InGameMenu igm;

    public Game(Context context) {
        super(context);
        lives = 5;
        paint = new Paint();
        pause_flg = 0;
        //Random r = new Random();
        Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.bird1_1);
        leftBird bird = new leftBird(context, b1);
        bird.setBirdInfo(-260, 0, 5);

        leftBird leftBird = new leftBird(context,b1);
        leftBird.setBirdInfo(-260,500, 10);
        leftBirds.add(bird);
        leftBirds.add(leftBird);


        Bitmap b2 = BitmapFactory.decodeResource(getResources(), R.drawable.rightbird3);
        rightBird birdd = new rightBird(context,b2);
        birdd.setBirdInfo(Resources.getSystem().getDisplayMetrics().widthPixels,0, 8);
        rightBirds.add(birdd);

        Bitmap igmBackground = BitmapFactory.decodeResource(getResources(), R.drawable.igmbackground);
        Bitmap igmResume = BitmapFactory.decodeResource(getResources(), R.drawable.resumegameoption);
        Bitmap igmMainMenu = BitmapFactory.decodeResource(getResources(), R.drawable.mainmenuoption);
        Bitmap igmExit = BitmapFactory.decodeResource(getResources(), R.drawable.exitgameoption);
        igm = new InGameMenu(context,igmBackground,igmResume,igmMainMenu,igmExit);

        heart = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        inGameMenu = BitmapFactory.decodeResource(getResources(), R.drawable.menu_button);

        rightShooter = BitmapFactory.decodeResource(getResources(), R.drawable.rightshooter);
        leftShooter = BitmapFactory.decodeResource(getResources(), R.drawable.leftshooter);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Iterator it = leftBirds.iterator();
        while (it.hasNext()) {
            leftBird bir = (leftBird) it.next();
            bir.draw(canvas);
            if(bir.isDead() && bir.getY() > Resources.getSystem().getDisplayMetrics().heightPixels)
                leftBirds.remove(bir);
        }

        ListIterator it2 = rightBirds.listIterator();
        while (it2.hasNext()){
            rightBird bird = (rightBird) it2.next();
            bird.draw(canvas);
            if(bird.isDead() && bird.getY() > Resources.getSystem().getDisplayMetrics().heightPixels)
                rightBirds.remove(bird);
        }

        //Drawing lives left as text
        int size = getResources().getDimensionPixelSize(R.dimen.myFontSize);
        paint.setColor(Color.BLACK);
        paint.setTextSize(size);
        canvas.drawText(String.valueOf(lives),10,size, paint);

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
        ListIterator it = leftBirds.listIterator();
        while (it.hasNext()) {
            leftBird bir = (leftBird) it.next();
            if(bir.getX() > Resources.getSystem().getDisplayMetrics().widthPixels && !bir.isDead()){
                lives--;
                leftBirds.remove(bir);
                return;
            }
        }

        ListIterator it2 = rightBirds.listIterator();
        while (it2.hasNext()){
            rightBird bird = (rightBird) it2.next();
            if(bird.getX()+bird.getImageWidth() < 0 && !bird.isDead())
            {
                lives--;
                rightBirds.remove(bird);
                return;
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
                ListIterator it2 = rightBirds.listIterator();
                while (it2.hasNext()) {
                    rightBird bir2 = (rightBird) it2.next();
                    if (x >= bir2.getX() && x < (bir2.getX()+bir2.getImageWidth()) && y >= bir2.getY() && y < (bir2.getY()+bir2.getImageHeight())) {
                        if(!bir2.isDead())
                        {
                            Bitmap deadbird3 = BitmapFactory.decodeResource(getResources(), R.drawable.deadbird3);
                            bir2.setImage(deadbird3);
                            bir2.setBirdSpeedY(10);
                            bir2.setDead(true);
                            return true;
                        }
                    }
                }

                //Move through all LEFT birds and check if the user clicked on any of the LEFT birds or not
                ListIterator it = leftBirds.listIterator();
                while (it.hasNext()) {
                    leftBird bir = (leftBird) it.next();
                    if (x >= bir.getX() && x < (bir.getX()+bir.getImageWidth()) && y >= bir.getY() && y < (bir.getY()+bir.getImageHeight())) {
                        if(!bir.isDead())
                        {
                            Bitmap deadbird1 = BitmapFactory.decodeResource(getResources(), R.drawable.deadbird1);
                            bir.setImage(deadbird1);
                            bir.setBirdSpeedY(10);
                            bir.setDead(true);
                            return true;
                        }
                    }
                }

                if(x >= Resources.getSystem().getDisplayMetrics().widthPixels-100 && x < (Resources.getSystem().getDisplayMetrics().widthPixels-100+inGameMenu.getWidth()) && y >= 4 && y < (4+inGameMenu.getHeight())){
                    pause_flg = 1;
                    ListIterator it3 = leftBirds.listIterator();
                    while (it3.hasNext()) {
                        leftBird bir = (leftBird) it3.next();
                        bir.pausedState();
                    }
                    ListIterator it4 = rightBirds.listIterator();
                    while (it4.hasNext()) {
                        rightBird bir2 = (rightBird) it4.next();
                        bir2.pausedState();
                    }
                    return true;
                }
            }

            if(pause_flg == 1){
                if(x >= 810 && x < (810+igm.getOptionWidth()) && y >= 265 && y < (265+igm.getOptionHeight()))
                {
                    Thread backToRunningStateThread = new Thread(){
                        public void run(){
                            ListIterator it = leftBirds.listIterator();
                            while (it.hasNext()) {
                                leftBird bir = (leftBird) it.next();
                                bir.runningState();
                            }

                            ListIterator it2 = rightBirds.listIterator();
                            while (it2.hasNext()){
                                rightBird bird = (rightBird) it2.next();
                                bird.runningState();
                            }
                        }
                    };
                    backToRunningStateThread.start();
                    pause_flg = 0;
                }
                if(x >= 810 && x < (810+igm.getOptionWidth()) && y >= 350 && y < (350+igm.getOptionHeight()))
                    getContext().startActivity(new Intent(this.getContext(),MainActivity.class));
                if(x >= 810 && x < (810+igm.getOptionWidth()) && y >= 435 && y < (435+igm.getOptionHeight()))
                    System.exit(0);
            }
    }
        return false;
    }
}
//                Toast.makeText(this.getContext().getApplicationContext(), "touch:" +  String.valueOf(x) + "|" + String.valueOf(y) + "bird: " + String.valueOf(bir.getX() + "|" + String.valueOf(y)) ,Toast.LENGTH_LONG).show();