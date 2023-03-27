package com.omshanti.workout.component;

import android.app.Application;

public class AppEnv extends Application {

    boolean gEnvInitialized = false;
    public AllSharePerference sharePerference;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public int gobalInit(){
        sharePerference = new AllSharePerference(this);
        gEnvInitialized = true;
        return 0;
    }
    //while shutting down app
    //use gEnvinitialized

    public boolean getEnvStatus(){
        return gEnvInitialized;
    }
}
