package com.omshanti.workout.daily;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.omshanti.workout.R;

import java.text.DecimalFormat;

public class BMIActivity extends AppCompatActivity {
    Spinner spinner;
    TextInputLayout layoutpound, layoutkilo, layoutfeet, layoutinch;
    TextInputEditText editTextAge, editTextpound, editTextkilo, editTextfeet, editTextinch;
    RadioGroup radioGroup;
    Button buttonSubmit;
    TextView textViewResult;
    //hold
    String gender = "";
    String type = "";
    String age = "";
    String weight = "";
    String pound = "";
    String feet = "";
    String inch = "";
    DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);
        init();
        //radio
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButtonMale)
                    gender = "male";
                else if (i == R.id.radioButtonFemale)
                    gender = "female";
            }
        });
        //spinner
        type = spinner.getSelectedItem().toString();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = spinner.getSelectedItem().toString();
                forward(type);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
//        forward(type);
        //click
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age = editTextAge.getText().toString();
                feet = editTextfeet.getText().toString();
                inch = editTextinch.getText().toString();
                double bmi = 0.0;
                if (type.equals("Metric BMI")){
                    weight = editTextkilo.getText().toString();
                    double inMeterfeet = Integer.parseInt(feet) * 0.3048;
                    double inMeterinch = Integer.parseInt(inch) * 0.0254;
                    double meterSquare = (inMeterfeet*inMeterinch) * (inMeterfeet*inMeterinch);
                    bmi = Integer.parseInt(weight) / meterSquare;//1.524*0.0762
                }else{
                    pound = editTextpound.getText().toString();
                    double inInchfeet = Integer.parseInt(feet) * 12;
                    double inInchinch = Integer.parseInt(inch);
                    bmi = (Integer.parseInt(weight) / (inInchfeet * inInchinch)) * 703;
                }
                textViewResult.setText("Your BMI is " + String.valueOf(decimalFormat.format(bmi)));

            }
        });
    }

    private void forward(String types) {
        Toast.makeText(this, ""+types, Toast.LENGTH_SHORT).show();
        if (types.equals("Metric BMI")){
            layoutpound.setVisibility(View.GONE);
            layoutkilo.setVisibility(View.VISIBLE);
        }else {
            layoutpound.setVisibility(View.VISIBLE);
            layoutkilo.setVisibility(View.GONE);
        }
    }

    private void init() {
        spinner = (Spinner) findViewById(R.id.spinner);
        layoutpound = (TextInputLayout) findViewById(R.id.lbInputLayout);
        layoutkilo = (TextInputLayout) findViewById(R.id.kgInputLayout);
        layoutfeet = (TextInputLayout) findViewById(R.id.feetInputLayout);
        layoutinch = (TextInputLayout) findViewById(R.id.inchInputLayout);
        editTextAge = (TextInputEditText) findViewById(R.id.ageEditText);
        editTextpound = (TextInputEditText) findViewById(R.id.lbEditText);
        editTextkilo = (TextInputEditText) findViewById(R.id.kgEditText);
        editTextfeet = (TextInputEditText) findViewById(R.id.feetEditText);
        editTextinch = (TextInputEditText) findViewById(R.id.inchEditText);
        radioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);
        buttonSubmit = (Button) findViewById(R.id.buttonCalculate);
        textViewResult = (TextView) findViewById(R.id.textViewShowResult);
    }
}