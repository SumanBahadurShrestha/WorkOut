package com.omshanti.workout.database.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DailyRepository {
    DailyRoomDatabase dailyRoomDatabase;
    DailyDao dailyDao;
    LiveData<List<DailyEntity>> listLiveData;
    List<DailyEntity> arrayList;

    public DailyRepository(Application application) {
        this.dailyRoomDatabase = DailyRoomDatabase.getDailyRoomDatabase(application);
        this.dailyDao = dailyRoomDatabase.dailyDao();
        this.listLiveData = dailyDao.getAll();
    }

    public void insertData(DailyEntity daily){
        DailyRoomDatabase.dataWriteExecutor.execute(() ->
                dailyDao.insert(daily));
    }

    public LiveData<List<DailyEntity>> getallData(){
        return listLiveData;
    }

    public DailyEntity getLastData(){
        DailyEntity daily = dailyDao.getLastData();
        return daily;
    }

    public DailyEntity getCurrentData(String currentDate){
        return dailyDao.getCurrentData(currentDate);
    }

//    public LiveData<List<DailyEntity>> updateCurrentWaterData(String target, String taken, int id){
//        LiveData<List<DailyEntity>> listLiveData = dailyDao.updateWater(target, taken, id);
//        return listLiveData;
//    }
//
//    public LiveData<List<DailyEntity>> updateCurrentWalkData(String target, String taken, int id){
//        LiveData<List<DailyEntity>> liveData = dailyDao.updateWalk(target, taken, id);
//        return liveData;
//    }

//    public void update(String target, String taken, int id){
//        new UpdateCourseAsyncTask(dailyDao).execute(target, taken, id);
//    }
}
