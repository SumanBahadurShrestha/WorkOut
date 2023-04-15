package com.omshanti.workout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.omshanti.workout.bottom.ChallengeFragment;
import com.omshanti.workout.bottom.DailyFragment;
import com.omshanti.workout.bottom.ReportFragment;
import com.omshanti.workout.bottom.SettingFragment;
import com.omshanti.workout.bottom.TrainingFragment;
import com.omshanti.workout.component.AppEnv;
import com.omshanti.workout.model.FilterHW;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    boolean doubleBackToExitPressedOnce = false;
    AppEnv appEnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appEnv = (AppEnv) getApplicationContext();
        ArrayList<String> stringArrayList = appEnv.sharePerference.getBodyPart();
        ArrayList<FilterHW> arrayList = appEnv.sharePerference.getHeiWei();
        System.out.println(
                appEnv.sharePerference.getGender() + " " +
                appEnv.sharePerference.getWorkoutType() + " " +
                        stringArrayList.size() + " " +
                appEnv.sharePerference.getGoal() + " " +
                appEnv.sharePerference.getActivityLevel() + " "+
                arrayList.size()
        );
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(itemSelectedListener);
        bottomNavigationView.setSelectedItemId(R.id.training);
        TrainingFragment fragment = new TrainingFragment();
        runFragment(fragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.daily:
                    DailyFragment dailyFragment = new DailyFragment();
                    runFragment(dailyFragment);
                    return true;
                case R.id.training:
                    TrainingFragment trainingFragment = new TrainingFragment();
                    runFragment(trainingFragment);
                    return true;
                case R.id.challenge:
                    ChallengeFragment challengeFragment = new ChallengeFragment();
                    runFragment(challengeFragment);
                    return true;
                case R.id.report:
                    ReportFragment reportFragment = new ReportFragment();
                    runFragment(reportFragment);
                    return true;
                case R.id.setting:
                    SettingFragment settingFragment = new SettingFragment();
                    runFragment(settingFragment);
                    return true;
            }
            return false;
        }
    };

    private void runFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}