package com.finalproject.hananavr.flying_birds;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "HighScoreTable")
public class High_Score {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "high_score")
    private int highScore;

    public High_Score(int highScore) {
        this.highScore = highScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
