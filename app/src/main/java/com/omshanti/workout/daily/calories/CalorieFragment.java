package com.omshanti.workout.daily.calories;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.omshanti.workout.R;

public class CalorieFragment extends Fragment {

    RadioGroup radioGroupSex;
    EditText editTextFeet, editTextInch, editTextKg, editTextYears;
    Spinner spinnerActivity;
    MaterialButton materialButton;
    EditText editTextCalories;
    int BMR;
    //hold value
    String gender = "male";
    int activtyPosition = 0;
    double[] activity = {1.2,1.4,1.6,1.75,2.0,2.3};

    public CalorieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calorie, container, false);
        radioGroupSex = (RadioGroup) view.findViewById(R.id.radioGroup);
        editTextFeet = (EditText) view.findViewById(R.id.editFeet);
        editTextInch = (EditText) view.findViewById(R.id.editInch);
        editTextKg = (EditText) view.findViewById(R.id.editKg);
        editTextYears = (EditText) view.findViewById(R.id.editYears);
        spinnerActivity = (Spinner) view.findViewById(R.id.spinnerActivity);
        materialButton = (MaterialButton) view.findViewById(R.id.bt_Calculate);
        editTextCalories = (EditText) view.findViewById(R.id.editCalories);
        spinnerActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                activtyPosition = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        radioGroupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioMale:
                        gender = "male";
                        break;
                    case R.id.radioFemale:
                        gender = "female";
                        break;
                }
            }
        });
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feet = editTextFeet.getText().toString().trim();
                String inch = editTextInch.getText().toString().trim();
                String kg = editTextKg.getText().toString().trim();
                String years = editTextYears.getText().toString().trim();
                String activit = String.valueOf(activity[activtyPosition]);
                if (feet.equals("") && kg.equals("") && years.equals(""))
                    Toast.makeText(getContext(), "Empty value", Toast.LENGTH_SHORT).show();
                int intkg = Integer.parseInt(kg);
                int intheight = (int) (Integer.parseInt(feet)*30.48 + Integer.parseInt(inch)*2.54);
                int intage = Integer.parseInt(years);
                if (gender.equals("male")){
                    BMR = (int) ((10 * intkg ) + (6.25 * intheight) - (5 * intage) + 5);
                }else{
                    BMR = (int) ((10 * intkg ) + (6.25 * intheight) - (5 * intage) - 161);
                }
                double value = BMR * Double.parseDouble((activit));
                editTextCalories.setText(value + "kcal / day");
            }
        });

        return view;
    }
}