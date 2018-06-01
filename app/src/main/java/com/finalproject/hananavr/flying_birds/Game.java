package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Game extends View {

    ArrayList<leftBird> leftBirds = new ArrayList<leftBird>();
    ArrayList<rightBird> rightBirds = new ArrayList<rightBird>();


    public Game(Context context) {
        super(context);
        Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.bird1_1);
        leftBird bird = new leftBird(context, b1);
        //Random r = new Random();
        bird.setBirdInfo(0, 0, 5);

        leftBird leftBird = new leftBird(context,b1);
        leftBird.setBirdInfo(0,500, 10);
        leftBirds.add(bird);
        leftBirds.add(leftBird);


        Bitmap b2 = BitmapFactory.decodeResource(getResources(), R.drawable.rightbird3);
        rightBird birdd = new rightBird(context,b2);
        birdd.setBirdInfo(Resources.getSystem().getDisplayMetrics().widthPixels - 50,0, 8);
        rightBirds.add(birdd);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Iterator it = leftBirds.iterator();
        while (it.hasNext()) {
            leftBird bir = (leftBird) it.next();
            bir.draw(canvas);
        }

        ListIterator it2 = rightBirds.listIterator();
        while (it2.hasNext()){
            rightBird bird = (rightBird) it2.next();
            bird.draw(canvas);
        }
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();

            //Move through all RIGHT birds and check if the user clicked on any of the RIGHT birds or not 
            ListIterator it2 = rightBirds.listIterator();
            while (it2.hasNext()) {
                rightBird bir2 = (rightBird) it2.next();
                if (x >= bir2.getX() && x < (bir2.getX()+bir2.getImageWidth()) && y >= bir2.getY() && y < (bir2.getY()+bir2.getImageHeight())) {
                    rightBirds.remove(bir2);
                    return true;
                }
            }

            //Move through all LEFT birds and check if the user clicked on any of the LEFT birds or not
            ListIterator it = leftBirds.listIterator();
            while (it.hasNext()) {
                leftBird bir = (leftBird) it.next();
                if (x >= bir.getX() && x < (bir.getX()+bir.getImageWidth()) && y >= bir.getY() && y < (bir.getY()+bir.getImageHeight())) {
                    leftBirds.remove(bir);
                    return true;
                }
            }
        }
        return false;
    }
}
//                Toast.makeText(this.getContext().getApplicationContext(), "touch:" +  String.valueOf(x) + "|" + String.valueOf(y) + "bird: " + String.valueOf(bir.getX() + "|" + String.valueOf(y)) ,Toast.LENGTH_LONG).show();