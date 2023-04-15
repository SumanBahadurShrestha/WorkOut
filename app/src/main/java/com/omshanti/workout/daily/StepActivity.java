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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.omshanti.workout.R;
import com.omshanti.workout.component.AllSharePerference;
import com.omshanti.workout.component.AppEnv;

public class StepActivity extends AppCompatActivity implements SensorEventListener {
    AppEnv appEnv;
    //calculation
    Spinner spinner;
    TextView textViewstep;
    //display
    TextView textViewStep, textViewX, textViewY, textViewZ;
    //button
    Button button;
    //sensor Manager
    SensorManager sensorManager;
    Sensor stepSensor;
    // Gravity for accelerometer data
    private float[] gravity = new float[3];
    // smoothed values
    private float[] smoothed = new float[3];
    //value store
    //boolean running = false;
    float totalsteps = 0, prevTotalStep = 0;
    int stepCount = 0, pointtoStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        appEnv = (AppEnv) getApplicationContext();
        Intent intent = getIntent();
        Boolean already = intent.getBooleanExtra("setStep", true);
        System.out.println(already); //true
        initget();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("eya eya ho " + spinner.getSelectedItem().toString() + spinner.getSelectedItemId());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appEnv.sharePerference.setStepGoal(false);
            }
        });
        loadData();
        resetStep();
//        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
    }

    private void initget() {
        //calculation
        textViewstep = (TextView) findViewById(R.id.step_total_step);
        spinner = (Spinner) findViewById(R.id.spinner_active_level);
        button = (Button) findViewById(R.id.button);
        textViewX = (TextView) findViewById(R.id.textviewX);
        textViewY = (TextView) findViewById(R.id.textviewY);
        textViewZ = (TextView) findViewById(R.id.textviewZ);
        textViewStep = (TextView) findViewById(R.id.tv_stepsTaken);
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
                return true;
            }
        });
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        int s = sharedPreferences.getInt("key1", 0);
        if (s != 0) {
            stepCount = s;
            textViewStep.setText(String.valueOf(s));
        }
    }

    protected float[] lowPassFilter( float[] input, float[] output ) {
        if ( output == null ) return input;
        for ( int i=0; i<input.length; i++ ) {
            output[i] = output[i] + 1.0f * (input[i] - output[i]);
        }
        return output;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // we need to use a low pass filter to make data smoothed
        smoothed = lowPassFilter(sensorEvent.values, gravity);
        gravity[0] = smoothed[0];
        gravity[1] = smoothed[1];
        gravity[2] = smoothed[2];
        totalsteps = gravity[1];
            //measure if a step is taken
        if (Math.abs(totalsteps - prevTotalStep) > 6){
            stepCount++;
            textViewStep.setText(String.valueOf(stepCount));
        }
        //display value
        textViewX.setText(String.valueOf(gravity[0]));
        textViewY.setText(String.valueOf(gravity[1]));
        textViewZ.setText(String.valueOf(gravity[2]));
        //store previous Y
        prevTotalStep = gravity[1];
//            int currentStep = (int) (totalsteps - prevTotalStep);
//            if (sensorEvent.values[0] > 6)

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("key1", Integer.parseInt(textViewStep.getText().toString()));
        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    @Override
    protected void onStop() {
        super.onStop();
        //remove listener
        sensorManager.unregisterListener(this, stepSensor);
    }
}