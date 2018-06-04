package com.finalproject.hananavr.flying_birds;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface High_ScoreDao {
    @Query("SELECT * FROM HighScoreTable")
    List<High_Score> getHighScore();

    @Insert
    void insertHighScore(High_Score high_score);

    @Update
    void updateHighScore(High_Score high_score);
}
