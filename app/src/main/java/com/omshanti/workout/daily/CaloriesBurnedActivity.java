package com.omshanti.workout.daily;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.omshanti.workout.R;
import com.omshanti.workout.daily.calories.CalorieFragment;
import com.omshanti.workout.daily.calories.CaloriesBrnFragment;
import com.omshanti.workout.daily.calories.SleepFragment;

public class CaloriesBurnedActivity extends AppCompatActivity {
    FrameLayout fragmentContainer;
    ColorStateList def;
    TextView item1, item2, item3;
    TextView select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_burned);
//        getSupportFragmentManager().beginTransaction().add(R.id.fram_container,new CaloriesCalFragment()).commit();
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        select = findViewById(R.id.select);
        def = item2.getTextColors();

        fragmentContainer =(FrameLayout) findViewById(R.id.fram_container);
        CaloriesBrnFragment calFragment = new CaloriesBrnFragment();
        runFragment(calFragment);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item1:
                select.animate().x(0).setDuration(100);
                item1.setTextColor(Color.WHITE);
                item2.setTextColor(def);
                item3.setTextColor(def);
                CaloriesBrnFragment calFragment = new CaloriesBrnFragment();
                runFragment(calFragment);
                break;
            case R.id.item2:
                item1.setTextColor(def);
                item2.setTextColor(Color.WHITE);
                item3.setTextColor(def);
                int size2 = item2.getWidth();
                select.animate().x(size2).setDuration(100);
                CalorieFragment calorieFragment = new CalorieFragment();
                runFragment(calorieFragment);
                break;
            case R.id.item3:
                item1.setTextColor(def);
                item2.setTextColor(def);
                item3.setTextColor(Color.WHITE);
                int size3 = item3.getWidth() * 2;
                select.animate().x(size3).setDuration(100);
                SleepFragment sleepFragment = new SleepFragment();
                runFragment(sleepFragment);
                break;
        }
    }

    private void runFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fram_container, fragment);
        fragmentTransaction.commit();
    }
}