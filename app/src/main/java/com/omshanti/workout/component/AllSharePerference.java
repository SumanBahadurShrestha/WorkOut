package com.omshanti.workout.component;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

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
    public boolean setBodyPart(String gender) {
        Editor editor =sharedPreferences.edit();
        editor.putString("selectedArea", gender);
        editor.commit();
        return true;
    }
    public String getBodyPart() {
        return sharedPreferences.getString("selectedArea", "");
    }
    //Main Goal
    public boolean setGoal(String gender) {
        Editor editor =sharedPreferences.edit();
        editor.putString("selectedGoal", gender);
        editor.commit();
        return true;
    }
    public String getGoal() {
        return sharedPreferences.getString("selectedGoal", "");
    }
    //Activity Level
    public boolean setActivityLevel(String gender) {
        Editor editor =sharedPreferences.edit();
        editor.putString("selectedActivityLevel", gender);
        editor.commit();
        return true;
    }
    public String getActivityLevel() {
        return sharedPreferences.getString("selectedActivityLevel", "");
    }
    //Weight & Height
    public boolean setHeiWei(String gender) {
        Editor editor =sharedPreferences.edit();
        editor.putString("selectedHeightWeight", gender);
        editor.commit();
        return true;
    }
    public String getHeiWei() {
        return sharedPreferences.getString("selectedHeightWeight", "");
    }

}
