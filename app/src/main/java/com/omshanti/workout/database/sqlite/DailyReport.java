package com.omshanti.workout.database.sqlite;

public class DailyReport {
    int id, walkTarget, walked, waterTarget, watered;
    String date;

    public DailyReport(int id, int walkTarget, int walked, int waterTarget, int watered, String date) {
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

    public int getWalkTarget() {
        return walkTarget;
    }

    public int getWalked() {
        return walked;
    }

    public int getWaterTarget() {
        return waterTarget;
    }

    public int getWatered() {
        return watered;
    }

    public String getDate() {
        return date;
    }
}
