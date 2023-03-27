package com.omshanti.workout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.omshanti.workout.component.AppEnv;
import com.omshanti.workout.filter.GenderFragment;

public class FilterContainerActivity extends AppCompatActivity {
    AppEnv appEnv;
    FrameLayout fragmentContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_container);
        appEnv = (AppEnv) getApplicationContext();
        fragmentContainer =(FrameLayout) findViewById(R.id.fragmentContainer);
        GenderFragment genderFragment = new GenderFragment();
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, genderFragment);
        fragmentTransaction.commit();

    }
}