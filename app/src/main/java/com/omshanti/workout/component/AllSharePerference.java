package com.omshanti.workout.component;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.omshanti.workout.model.FilterHW;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AllSharePerference {
    private Context mContext;
    SharedPreferences sharedPreferences;
    OnSharedPreferenceChangeListener listener;

    public AllSharePerference(Context mContext) {
        this.mContext = mContext;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }
    //OnBoarding Screen
    public boolean setFirstTimeStart(boolean sfts) {
        Editor editor = sharedPreferences.edit();
        editor.putBoolean("FirstTimeStartFlag", sfts);
        editor.commit();
        return true;
    }
    public boolean getFirstTimeStart() {
        return sharedPreferences.getBoolean("FirstTimeStartFlag", true);
    }
    //Gender
    public boolean setGender(String gender) {
        Editor editor =sharedPreferences.edit();
        editor.putString("selectedGender", gender);
        editor.commit();
        return true;
    }
    public String getGender() {
        return sharedPreferences.getString("selectedGender", "");
    }
    //Workout Type
    public boolean setWorkoutType(String gender) {
        Editor editor =sharedPreferences.edit();
        editor.putString("selectedPlace", gender);
        editor.commit();
        return true;
    }
    public String getWorkoutType() {
        return sharedPreferences.getString("selectedPlace", "");
    }
    //Body Part
    public boolean setBodyPart(ArrayList<String> stringArrayList) {
        Editor editor =sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(stringArrayList);
        editor.putString("selectedArea", json);
        editor.commit();
        return true;
    }
    public ArrayList<String> getBodyPart() {
        Gson gson =new Gson();
        String json = sharedPreferences.getString("selectedArea", "");
        Type type = new TypeToken<List<String>>(){}.getType();
        return gson.fromJson(json,type);
    }
    //Main Goal
    public boolean setGoal(String goal) {
        Editor editor =sharedPreferences.edit();
        editor.putString("selectedGoal", goal);
        editor.commit();
        return true;
    }
    public String getGoal() {
        return sharedPreferences.getString("selectedGoal", "");
    }
    //Activity Level
    public boolean setActivityLevel(String level) {
        Editor editor =sharedPreferences.edit();
        editor.putString("selectedActivityLevel", level);
        editor.commit();
        return true;
    }
    public String getActivityLevel() {
        return sharedPreferences.getString("selectedActivityLevel", "");
    }
    //Weight & Height
    public boolean setHeiWei(ArrayList<FilterHW> hw) {
        Editor editor =sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(hw);
        editor.putString("selectedHeightWeight", json);
        editor.commit();
        return true;
    }
    public String getTestFormate(){
        return sharedPreferences.getString("selectedHeightWeight", "");
    }
    public ArrayList<FilterHW> getHeiWei() {
        Gson gson =new Gson();
        String json = sharedPreferences.getString("selectedHeightWeight", "");
        Type type = new TypeToken<List<FilterHW>>(){}.getType();
        return gson.fromJson(json,type);
    }
    //Daily Fragment Part
    //Step Count
    public boolean setStepGoal(boolean step) {
        Editor editor =sharedPreferences.edit();
        editor.putBoolean("stepTarget", step);
        editor.commit();
        return true;
    }
    public boolean getStepGoal() {
        return sharedPreferences.getBoolean("stepTarget", true);
    }
    public boolean setStep(boolean step) {
        Editor editor =sharedPreferences.edit();
        editor.putBoolean("stepTarget", step);
        editor.commit();
        return true;
    }
    public boolean getStep() {
        return sharedPreferences.getBoolean("stepTarget", true);
    }
    //Water Track
    public boolean setWater(int take, int taken) {
        Editor editor =sharedPreferences.edit();
        editor.putInt("waterTarget", take);
        editor.putInt("waterTaken", taken);
        editor.commit();
        return true;
    }
    public int[] getWater() {
        int target = sharedPreferences.getInt("waterTarget", 0);
        int taken = sharedPreferences.getInt("waterTaken", 0);
//        String trow = String.valueOf(target)+"/"+String.valueOf(taken);
        return new int[] {target , taken};
    }
}
