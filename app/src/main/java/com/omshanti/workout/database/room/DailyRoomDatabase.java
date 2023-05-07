package com.omshanti.workout.database.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {DailyEntity.class}, version = 1, exportSchema = false)
public abstract class DailyRoomDatabase extends RoomDatabase {
    public abstract DailyDao dailyDao();
    private static volatile DailyRoomDatabase dailyRoomDatabase;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService dataWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static DailyRoomDatabase getDailyRoomDatabase(final Context context){
        if (dailyRoomDatabase == null){
            synchronized (DailyRoomDatabase.class){
                if (dailyRoomDatabase == null){
                    dailyRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            DailyRoomDatabase.class,
                            "DailyTrack")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return dailyRoomDatabase;
    }
}
