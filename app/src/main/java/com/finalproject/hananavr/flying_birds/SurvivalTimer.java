package com.finalproject.hananavr.flying_birds;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SurvivalTimer {
    private int secondsOneness;
    private int secondsTenths;
    private int minutesOneness;
    private int minutesTenths;

    static Runnable timerRunnable;
    static ScheduledExecutorService timerService;
    static Future<?> timerFuture;

    public SurvivalTimer(){
        secondsOneness = 0;
        secondsTenths = 0;
        minutesOneness = 0;
        minutesTenths = 0;
    }

    public void startSurvivalTimer() {
        timerRunnable = new Runnable() {
            public void run() {
                secondsOneness++;
                if(secondsOneness == 10){
                   secondsTenths++;
                    secondsOneness = 0;
                }
                if(secondsTenths == 6){
                    secondsTenths = 0;
                    minutesOneness++;
                }
            }
        };
        timerService = Executors.newSingleThreadScheduledExecutor();
        timerFuture = timerService.scheduleAtFixedRate(timerRunnable, 1, 1, TimeUnit.SECONDS);
    }

    public int getSecondsOneness() {
        return secondsOneness;
    }


    public int getSecondsTenths() {
        return secondsTenths;
    }


    public int getMinutesOneness() {
        return minutesOneness;
    }


    public int getMinutesTenths() {
        return minutesTenths;
    }
}
