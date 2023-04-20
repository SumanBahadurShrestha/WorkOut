package com.omshanti.workout.database.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.omshanti.workout.database.room.DailyEntity;

import java.sql.Date;
import java.util.ArrayList;

public class DailyDatabaseHandler extends SQLiteOpenHelper {
    private static DailyDatabaseHandler sInstance;
    Context mContext;
    SQLiteDatabase sqLiteDatabase;
    protected static final String DATABASE_NAME = "dailyTakeReport.db";
    protected static final String TABLE_NAME = "DailyTakeReport";
    private static final int DATABASE_VERSION = 1;
    ArrayList<DailyReport> arrayList = new ArrayList<DailyReport>();
    private ContentValues contentValues;
    Cursor cursor;

    public DailyDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
        initDB();
    }

    public void initDB() {
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "walkTarget INTEGER," +
                "walked INTEGER," +
                "waterTarget INTEGER," +
                "warted INTEGER," +
                "date DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public static synchronized DailyDatabaseHandler getInstance(Context context, String extender){
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null){
            sInstance = new DailyDatabaseHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    public void cleanUp(){
        sqLiteDatabase.execSQL("VACUUM");
    }

    public SQLiteDatabase getDailyDBConn(){
        return sqLiteDatabase;
    }
    //insert------------------------------------------------------------------------------------------------------------------------------------
    public boolean insertData(int walk, int walked,
                              int water, int watered, String date){
        contentValues = new ContentValues();
//        contentValues.put("id", id);
        contentValues.put("walkTarget", walk);
        contentValues.put("walked", walked);
        contentValues.put("waterTarget", water);
        contentValues.put("warted", watered);
        contentValues.put("date", date);
        long val = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (val == -1)
            return false;
        else
            return true;
    }
    //count------------------------------------------------------------------------------------------------------------------------------------
    public int getItemCount(){
        int count = 0;
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        count = cursor.getCount();
        cursor.close();
        return count;
    }
    //get------------------------------------------------------------------------------------------------------------------------------------
    //get all data
    public ArrayList<DailyReport> getAllData(){
        Cursor cursor;
        int count = 0;
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        count = cursor.getCount();
        if (count > 0){
            cursor.moveToFirst();
            for (int i = 0; i < count; i++){
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                int walk = cursor.getInt(cursor.getColumnIndexOrThrow("walkTarget"));
                int walked = cursor.getInt(cursor.getColumnIndexOrThrow("walked"));
                int water = cursor.getInt(cursor.getColumnIndexOrThrow("waterTarget"));
                int watered = cursor.getInt(cursor.getColumnIndexOrThrow("warted"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                DailyReport dailyReport = new DailyReport(
                        id, walk, walked, water, watered, date
                );
                arrayList.add(dailyReport);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrayList;
    }
    //get last row data
    public DailyReport getLastData(){
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY id DESC LIMIT 1", null);
        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        int walk = cursor.getInt(cursor.getColumnIndexOrThrow("walkTarget"));
        int walked = cursor.getInt(cursor.getColumnIndexOrThrow("walked"));
        int water = cursor.getInt(cursor.getColumnIndexOrThrow("waterTarget"));
        int watered = cursor.getInt(cursor.getColumnIndexOrThrow("warted"));
        String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
        DailyReport dailyReport = new DailyReport(
                id, walk, walked, water, watered, date
        );
        cursor.close();
        return dailyReport;
    }
    //get row data by id
    public DailyReport getById(int iid){
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + iid, null );
        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        int walk = cursor.getInt(cursor.getColumnIndexOrThrow("walkTarget"));
        int walked = cursor.getInt(cursor.getColumnIndexOrThrow("walked"));
        int water = cursor.getInt(cursor.getColumnIndexOrThrow("waterTarget"));
        int watered = cursor.getInt(cursor.getColumnIndexOrThrow("warted"));
        String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
        DailyReport dailyReport = new DailyReport(
                id, walk, walked, water, watered, date
        );
        cursor.close();
        return dailyReport;
    }
    //update------------------------------------------------------------------------------------------------------------------------------------
    public boolean updateTargetWater(int id, int target){
        contentValues = new ContentValues();
        contentValues.put("waterTarget", target);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "id = "+ id, null );
        return true;
    }
    public boolean updateTargetStep(int id, int target){
        contentValues = new ContentValues();
        contentValues.put("walkTarget", target);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "id = "+ id, null );
        return true;
    }
    public boolean updateTargetWaterStep(int id, int step, int water){
        contentValues = new ContentValues();
        contentValues.put("walkTarget", step);
        contentValues.put("waterTarget", water);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "id = "+ id, null );
        return true;
    }
    public boolean updateAddWater(int id, int taken){
        contentValues = new ContentValues();
        contentValues.put("warted", taken);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "id = "+ id, null );
        return true;
    }
    public boolean updateAddStep(int id, int taken){
        contentValues = new ContentValues();
        contentValues.put("walked", taken);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "id = "+ id, null );
        return true;
    }
    //delete------------------------------------------------------------------------------------------------------------------------------------
    public boolean deleteData(String id){
        sqLiteDatabase.delete(TABLE_NAME, "id = ? ", new String[]{id});
        return true;
    }
}
