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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.omshanti.workout.R;
import com.omshanti.workout.component.AppEnv;

public class WaterActivity extends AppCompatActivity {
    AppEnv appEnv;
    TextInputEditText textInputEditTextAge, textInputEditTextWeight;
    MaterialButton materialButtonCal;
    TextView textViewWaterNeeded;
    //
    TextView textViewShowWaterTook;
    ProgressBar progressBarCircle;
    Button buttonAdd;
    int process = 0;
    int totalStep = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        appEnv = (AppEnv) getApplicationContext();
        textInputEditTextAge = (TextInputEditText) findViewById(R.id.age_editText);
        textInputEditTextWeight = (TextInputEditText) findViewById(R.id.weight_editText);
        materialButtonCal = (MaterialButton) findViewById(R.id.mbutton_calculate);
        textViewWaterNeeded = (TextView) findViewById(R.id.textView_waterTake);
        progressBarCircle = (ProgressBar) findViewById(R.id.progressBar);
        textViewShowWaterTook = (TextView) findViewById(R.id.textView_watertook);
        buttonAdd = (Button) findViewById(R.id.buttonAddWater);
        //getValue
        int[] savedwater = appEnv.sharePerference.getWater();
        if (savedwater[0] > 0){
            textViewShowWaterTook.setText(String.valueOf(savedwater[1]) + "/" + String.valueOf(savedwater[0]));
            process = savedwater[1];
            totalStep = savedwater[0];
            progressBarCircle.setMax(totalStep);
            progressBarCircle.setProgress(savedwater[1]);
        }else{
            textViewShowWaterTook.setText("");
            process = 0;
            totalStep = 0;
            progressBarCircle.setMax(totalStep);
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
                    totalStep = tWater;
                    textViewShowWaterTook.setVisibility(View.VISIBLE);
                    textViewShowWaterTook.setText("/" + String.valueOf(totalStep));
                    appEnv.sharePerference.setWater(totalStep, 0);
                }
            }
        });
        //onclick
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (process <= totalStep) {

                    process += 1;
                    UpdateProgress();
                }
            }
        });
    }

    private void UpdateProgress() {
        textViewShowWaterTook.setText(String.valueOf(process)+ "/" + String.valueOf(totalStep));
        progressBarCircle.setMax(totalStep);
        progressBarCircle.setProgress(process);
        appEnv.sharePerference.setWater(totalStep, process);
        if (process == totalStep){
            Toast.makeText(this, "water completed", Toast.LENGTH_SHORT).show();
        }
    }
}