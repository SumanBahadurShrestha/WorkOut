package com.omshanti.workout.database.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DailyDao {
    @Insert
    void insert(DailyEntity daily);

    @Query("SELECT * FROM DailyTrack")
    LiveData<List<DailyEntity>> getAll();

    @Query("SELECT * FROM DailyTrack WHERE date = :currentDate")
    DailyEntity getCurrentData(String currentDate);

    @Query("SELECT * FROM DailyTrack ORDER BY id DESC LIMIT 1")
    DailyEntity getLastData();

    @Query("UPDATE DailyTrack SET waterTarget= :wtarget, watered = :taken WHERE id = :id")
    void updateWater(String wtarget, String taken, int id);

    @Query("UPDATE DailyTrack SET walkTarget= :wtarget, walked = :taken WHERE id = :id")
    void updateWalk(String wtarget, String taken, int id);
}
