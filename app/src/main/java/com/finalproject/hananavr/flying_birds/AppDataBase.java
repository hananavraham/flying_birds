package com.finalproject.hananavr.flying_birds;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {High_Score.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {
    public abstract High_ScoreDao high_scoreDao();
}
