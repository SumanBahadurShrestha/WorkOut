package com.omshanti.workout.daily;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.omshanti.workout.R;
import com.omshanti.workout.component.AppEnv;
import com.omshanti.workout.database.sqlite.DailyDatabaseHandler;
import com.omshanti.workout.database.sqlite.DailyReport;

public class WaterActivity extends AppCompatActivity {
    AppEnv appEnv;
    TextInputEditText textInputEditTextAge, textInputEditTextWeight;
    MaterialButton materialButtonCal;
    TextView textViewWaterNeeded;
    ImageView imageView;
    //
    TextView textViewShowWaterTook;
    ProgressBar progressBarCircle;
    Button buttonSetValue;
    int process = 0;
    int totalGlasses = 0;
    //
    int savedwater;
    int takenglass;
    //database
    DailyDatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        appEnv = (AppEnv) getApplicationContext();
        databaseHandler = new DailyDatabaseHandler(this);
        databaseHandler.initDB();
        textInputEditTextAge = (TextInputEditText) findViewById(R.id.age_editText);
        textInputEditTextWeight = (TextInputEditText) findViewById(R.id.weight_editText);
        materialButtonCal = (MaterialButton) findViewById(R.id.mbutton_calculate);
        textViewWaterNeeded = (TextView) findViewById(R.id.textView_waterTake);
        progressBarCircle = (ProgressBar) findViewById(R.id.progressBar);
        imageView = (ImageView) findViewById(R.id.gender_outline);
        textViewShowWaterTook = (TextView) findViewById(R.id.textView_watertook);
        buttonSetValue = (Button) findViewById(R.id.buttonAddWater);
        //getValue

        if (appEnv.holdDbId == 0) {
            savedwater = appEnv.sharePerference.getWater();
            takenglass = 0;
        }
        else{
            DailyReport dailyReport = databaseHandler.getById(appEnv.holdDbId);
            savedwater = dailyReport.getWaterTarget();
            takenglass = dailyReport.getWatered();
        }
        //database
        if (savedwater > 0){
            textViewShowWaterTook.setText(String.valueOf(takenglass) + "/" + String.valueOf(savedwater));
            process = takenglass;
            totalGlasses = savedwater;
            progressBarCircle.setMax(totalGlasses);
            progressBarCircle.setProgress(takenglass);
        }else{
            textViewShowWaterTook.setText("");
            process = 0;
            totalGlasses = 0;
            progressBarCircle.setMax(totalGlasses);
            progressBarCircle.setProgress(0);
        }
        materialButtonCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String age = textInputEditTextAge.getText().toString().trim();
                String weight = textInputEditTextWeight.getText().toString().trim();
                if (age.equals("") || weight.equals("")){
                    //error
                }else{
                    int Iage = Integer.parseInt(age);
                    int Iweight = Integer.parseInt(weight);
                    int holdValue;
                    if (Iage <= 30){
                        holdValue = Iweight * 40;
                    }else if (Iage > 55){
                        holdValue = Iweight * 30;
                    }else{
                        holdValue = Iweight * 35;
                    }
                    double amt = holdValue / 28.3;
                    int tWater = (int) amt/8;
                    textViewWaterNeeded.setText("Normally you need: " + tWater + " glasses of water.");
                    totalGlasses = tWater;
                    textViewShowWaterTook.setVisibility(View.VISIBLE);
                    textViewShowWaterTook.setText("/" + String.valueOf(totalGlasses));
                    buttonSetValue.setText("Set Goal");
                    buttonSetValue.setVisibility(View.VISIBLE);
                }
            }
        });
        //onclick
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (process <= totalGlasses) {
                    process += 1;
                    UpdateProgress();
                }
            }
        });
        buttonSetValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appEnv.sharePerference.setWater(totalGlasses);
                Toast.makeText(WaterActivity.this, "setValue: " + totalGlasses, Toast.LENGTH_SHORT).show();
                if (appEnv.holdDbId == 0){
                    boolean result = databaseHandler.insertData(0, 0, 0, 0, appEnv.currentDate);
                    if (result){
                        boolean update = databaseHandler.updateTargetWaterStep(appEnv.holdDbId + 1, 0, totalGlasses);
                        if (update){
                            Toast.makeText(WaterActivity.this, "Add and update", Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(WaterActivity.this, "Add no update", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(WaterActivity.this, "not insert", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    boolean uUpdate = databaseHandler.updateTargetWater(appEnv.holdDbId, totalGlasses);
                    if (uUpdate)
                        Toast.makeText(WaterActivity.this, "update new target", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(WaterActivity.this, "nothing", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //show instruction
        Toast.makeText(WaterActivity.this, "Click person to add water...", Toast.LENGTH_SHORT).show();
    }

    private void UpdateProgress() {
        textViewShowWaterTook.setText(String.valueOf(process)+ "/" + String.valueOf(totalGlasses));
        progressBarCircle.setMax(totalGlasses);
        progressBarCircle.setProgress(process);
        databaseHandler.updateAddWater(appEnv.holdDbId, process);
        if (process == totalGlasses){
            Toast.makeText(this, "water completed", Toast.LENGTH_SHORT).show();
        }
    }
}