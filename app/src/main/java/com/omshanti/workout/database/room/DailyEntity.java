package com.omshanti.workout.database.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DailyTrack")
public class DailyEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String walkTarget;
    private String walked;
    private String waterTarget;
    private String watered;
    private String date;

    public DailyEntity(int id, String walkTarget, String walked, String waterTarget, String watered, String date) {
        this.id = id;
        this.walkTarget = walkTarget;
        this.walked = walked;
        this.waterTarget = waterTarget;
        this.watered = watered;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getWalkTarget() {
        return walkTarget;
    }

    public String getWalked() {
        return walked;
    }

    public String getWaterTarget() {
        return waterTarget;
    }

    public String getWatered() {
        return watered;
    }

    public String getDate() {
        return date;
    }
}
