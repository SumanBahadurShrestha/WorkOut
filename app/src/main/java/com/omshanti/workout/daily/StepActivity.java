package com.omshanti.workout.daily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.omshanti.workout.R;
import com.omshanti.workout.component.AllSharePerference;
import com.omshanti.workout.component.AppEnv;

public class StepActivity extends AppCompatActivity implements SensorEventListener {
    AppEnv appEnv;
    //display
    TextView textViewStep, textViewX, textViewY, textViewZ;
    //button
    Button button;
    //sensor Manager
    SensorManager sensorManager;
    Sensor stepSensor;
    float acceleration;
    //value store
    boolean running = false;
    double totalsteps = 0;
    double prevTotalStep = 0;
    int stepCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        appEnv = (AppEnv) getApplicationContext();
        Intent intent = getIntent();
        Boolean already = intent.getBooleanExtra("setStep", true);
        System.out.println(already); //true
//        button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                appEnv.sharePerference.setStep(false);
//            }
//        });
        textViewStep = findViewById(R.id.tv_stepsTaken);
        loadData();
        resetStep();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
    }

    private void resetStep() {
        textViewStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StepActivity.this, "long tap", Toast.LENGTH_SHORT).show();
            }
        });
        textViewStep.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                prevTotalStep = totalsteps;
                stepCount = 0;
                textViewStep.setText(String.valueOf(stepCount));
                saveData();
                return true;
            }
        });
    }
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("key1", (int) prevTotalStep);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        prevTotalStep = sharedPreferences.getInt("key1", 0);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (running){
            float x_ = sensorEvent.values[0];
            float y_ = sensorEvent.values[1];
            float z_ = sensorEvent.values[2];
            totalsteps = Math.sqrt(x_*x_+y_*y_+z_*z_);
            int currentStep = (int) (totalsteps - prevTotalStep);
            if (sensorEvent.values[0] > 6)
                stepCount++;
            textViewStep.setText(String.valueOf(stepCount));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        if (stepSensor == null)
            Toast.makeText(StepActivity.this, "No senor", Toast.LENGTH_SHORT).show();
        else
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregister sensor
        sensorManager.unregisterListener(this);
    }
}