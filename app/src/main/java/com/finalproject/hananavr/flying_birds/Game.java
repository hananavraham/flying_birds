package com.finalproject.hananavr.flying_birds;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

public class Game extends View {

    ArrayList<Bird1> birds = new ArrayList<Bird1>();

    public Game(Context context) {
        super(context);
        Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.bird1_1);
        Bird1 bird = new Bird1(context, b1);
        //Random r = new Random();
        bird.setCoord(300, 0);

        Bird1 bird1 = new Bird1(context,b1);
        bird1.setCoord(0,500);
        birds.add(bird);
        birds.add(bird1);

        bird = new Bird1(context,b1);
        bird.setCoord(Resources.getSystem().getDisplayMetrics().widthPixels - 200,300);
        birds.add(bird);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Iterator it = birds.iterator();
        while (it.hasNext()) {
            Bird1 bir = (Bird1) it.next();
            bir.draw(canvas);
        }
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            ListIterator it = birds.listIterator();
            while (it.hasNext()) {
                Bird1 bir = (Bird1) it.next();
//                Toast.makeText(this.getContext().getApplicationContext(), "touch:" +  String.valueOf(x) + "|" + String.valueOf(y) + "bird: " + String.valueOf(bir.getX() + "|" + String.valueOf(y)) ,Toast.LENGTH_LONG).show();
                if ((x - 100 <= bir.getX() && x + 100 >= bir.getX()) && (y - 200 <= bir.getY() && y +200 >= bir.getY())) {
                        birds.remove(bir);
                        return true;
                }
            }

//            for (int i = 0 ; i < birds.size() ; ++i){
//                if (x - 100 <= birds.get(i).getX() && x + 100 >= birds.get(i).getX()){
//                    birds.remove(i);
//                    return true;
//                }
//            }
        }
        return false;
    }
}
