package com.omshanti.workout.component;

import android.app.Application;
import android.widget.Toast;

import com.omshanti.workout.database.sqlite.DailyDatabaseHandler;
import com.omshanti.workout.database.sqlite.DailyReport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppEnv extends Application {

    boolean gEnvInitialized = false;
    public AllSharePerference sharePerference;
//    DailyRepository dailyRepository;
    public String currentDate;
    DailyDatabaseHandler databaseHandler;

    //hold
    public int holdDbId;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public int gobalInit(){
        sharePerference = new AllSharePerference(this);
        currentDate = getCurrentDate();
//        dailyRepository = new DailyRepository(this);
        databaseHandler = new DailyDatabaseHandler(this);
        gEnvInitialized = true;
//        getDataRow();
        checkSetValue();
        return 0;
    }
    //if target is set than start matching date, if date match do nothing else add new row and update set target and current date.
    private void checkSetValue() {
        int waterSharePer = sharePerference.getWater();
//        int step = sharePerference.getStep();
        int getDate = databaseHandler.getItemCount();
        if (getDate == 0){
            holdDbId = 0;
            System.out.println("no data at database..");
        }else{
            DailyReport dailyReport = databaseHandler.getLastData();
            String data = dailyReport.getDate();
            System.out.println("Date Print here: " + data);//2023-04-20
            String currentDate = getCurrentDate();
            System.out.println("Current Date: " + currentDate);
            //if match active match id
            if (currentDate.equals(data)) {
                System.out.println("Current Date: " + dailyReport.getId());
                holdDbId = dailyReport.getId();
            }
            //else create empty data with current date and active that id
            else{
                databaseHandler.initDB();
                databaseHandler.insertData(0,0,waterSharePer,0,currentDate);
//                databaseHandler.updateTargetWaterStep(dailyReport.getId(), 0, waterSharePer);
                DailyReport lastData = databaseHandler.getLastData();
                holdDbId = lastData.getId();
            }
        }
    }

    private String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
    //while shutting down app
    //use gEnvinitialized
    //database
    public DailyDatabaseHandler getDatabase(){
        return databaseHandler;
    }
//    public DailyRepository getDailyRepository(){
//        return dailyRepository;
//    }
//    public void getDataRow(){
//        //current Date
//        System.out.println("filter Row: " + currentDate);
//        DailyEntity daily = getDailyRepository().getLastData();
//        if (daily == null){
//            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
//        }
//    }

    public boolean getEnvStatus(){
        return gEnvInitialized;
    }
}
