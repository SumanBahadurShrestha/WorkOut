package com.omshanti.workout.daily.calories;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.omshanti.workout.R;
import com.omshanti.workout.model.CaloriesBurned;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CaloriesBrnFragment extends Fragment {
    Context mContext;
    EditText editTextWeight, editTextHour, editTextMins;
    EditText editTextEnergyBurn, editTextEnergyBurnPerHour, editTextWeightLoss;
    MaterialButton materialButton;
    Spinner spinnerActivity;
    ArrayList<CaloriesBurned> arrayList;
    ArrayList<String> activity;
    int activityPosition = 0;
    DecimalFormat oneDForm = new DecimalFormat("#.#");
    DecimalFormat twoDForm = new DecimalFormat("#.##");
    int i_weight, i_hour, i_minute;
    TextView half, full;

    public CaloriesBrnFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calories_cal, container, false);
        mContext = getContext();
        arrayList = new ArrayList<CaloriesBurned>();
        activity = new ArrayList<String>();
        addDataInArray();
        editTextWeight = (EditText) view.findViewById(R.id.ed_weight);
        spinnerActivity = (Spinner) view.findViewById(R.id.spin_activity);
        editTextHour = (EditText) view.findViewById(R.id.ed_hrs);
        editTextMins = (EditText) view.findViewById(R.id.ed_min);
        editTextMins = (EditText) view.findViewById(R.id.ed_min);
        materialButton = (MaterialButton) view.findViewById(R.id.bt_Calculate);
        editTextEnergyBurn = (EditText) view.findViewById(R.id.et_energy_burn);
        editTextEnergyBurnPerHour = (EditText) view.findViewById(R.id.ed_eb_perhrs);
        editTextWeightLoss = (EditText) view.findViewById(R.id.ed_weight_loss);
        half = (TextView) view.findViewById(R.id.loseHalfKg);
        full = (TextView) view.findViewById(R.id.loseOneKg);
        //for loop
        for( int i = 0; i < arrayList.size(); i++ ){
            activity.add(arrayList.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, activity);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActivity.setAdapter(adapter);
        spinnerActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String QuestionName = spinnerActivity.getSelectedItem().toString();
                double value = arrayList.get(position).getValue();
                Log.i("Name"," : "+ QuestionName);
                Log.i("Id"," : "+ value);
                activityPosition = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        //on click
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = editTextWeight.getText().toString().trim();
                String hour = editTextHour.getText().toString().trim();
                String minute = editTextMins.getText().toString().trim();
                if (weight.equals(""))
                    Toast.makeText(mContext, "empty weight", Toast.LENGTH_SHORT).show();
                else if (hour.equals("") && minute.equals(""))
                    Toast.makeText(mContext, "empty time", Toast.LENGTH_SHORT).show();
                else if (hour.equals("") && !minute.equals("")) {
                    i_weight = Integer.parseInt(weight);
                    i_hour = 0;
                    i_minute = Integer.parseInt(minute);
                }else if (!hour.equals("") && minute.equals("")) {
                    i_weight = Integer.parseInt(weight);
                    i_hour = Integer.parseInt(hour);
                    i_minute = 0;
                }else{
                    i_weight = Integer.parseInt(weight);
                    i_hour = Integer.parseInt(hour);
                    i_minute = Integer.parseInt(minute);
                }
                double i_activity = arrayList.get(activityPosition).getValue();
                double value1 = (i_hour*60*60 + i_minute*60) * i_activity*3.5*i_weight;
                double value2 = value1 /12000;
                editTextEnergyBurn.setText(Double.valueOf(oneDForm.format(value2)) + " kcal");
                double perValue1 = (60*60) * i_activity*3.5*i_weight;
                double perValue = perValue1/12000;
                editTextEnergyBurnPerHour.setText(Double.valueOf(oneDForm.format(perValue))  + " kacl/hrs");
                double inKg = value2/7700;
                editTextWeightLoss.setText(Double.valueOf(twoDForm.format(inKg))  + " kg");
                //extra
                double y = (0.5*7700)/perValue;
                double z = (1*7700)/perValue;
                half.setText("To lose 0.5 kg of fat, you should perform this activity for " + (int) y + " hours.");
                full.setText("To lose 1 kg of fat, you should perform this activity for " + (int) z+ " hours.");
            }
        });

        return view;
    }

    private void addDataInArray() {
        arrayList.add(new CaloriesBurned("\uD83D\uDC83 Aerobics", 6.83));
        arrayList.add(new CaloriesBurned("⛹ Baseball, softball", 5));
        arrayList.add(new CaloriesBurned("⛹ Basketball", 8));
        arrayList.add(new CaloriesBurned("\uD83C\uDFB1 Billiards", 2.5));
        arrayList.add(new CaloriesBurned("\uD83C\uDFB3 Bowling", 3));
        arrayList.add(new CaloriesBurned("\uD83D\uDD17 Climbing, spelunking, caving", 8));
        arrayList.add(new CaloriesBurned("\uD83D\uDEB2 Cycling", 9.5));
        arrayList.add(new CaloriesBurned("\uD83D\uDC83 Dancing", 4.5));
        arrayList.add(new CaloriesBurned("\uD83C\uDFC7 Equestrian sports", 5.33));
        arrayList.add(new CaloriesBurned("⚔ Fencing", 6));
        arrayList.add(new CaloriesBurned("\uD83C\uDFA3 Fishing", 4.5));
        arrayList.add(new CaloriesBurned("\uD83C\uDFC8 Football (american)", 8));
        arrayList.add(new CaloriesBurned("⚽ Football (soccer)", 7));
        arrayList.add(new CaloriesBurned("⛳ Golfing", 3.75));
        arrayList.add(new CaloriesBurned("\uD83C\uDFBD Gymnastics", 4));
        arrayList.add(new CaloriesBurned("⛰ Hiking", 6));
        arrayList.add(new CaloriesBurned("\uD83D\uDC27 Hockey", 8));
        arrayList.add(new CaloriesBurned("⛸ Ice skating", 7));
        arrayList.add(new CaloriesBurned("\uD83C\uDFC4 Kitesurfing, windsurfing", 11));
        arrayList.add(new CaloriesBurned("\uD83D\uDE4F Martial arts", 10));
        arrayList.add(new CaloriesBurned("\uD83C\uDFBE Racquet sports", 8.5));
        arrayList.add(new CaloriesBurned("Rollerblading", 6));
        arrayList.add(new CaloriesBurned("⛵ Rowing", 4.64));
        arrayList.add(new CaloriesBurned("\uD83C\uDFC9 Rugby", 10));
        arrayList.add(new CaloriesBurned("\uD83C\uDFC3 Running", 9.8));
        arrayList.add(new CaloriesBurned("\uD83C\uDFE9 Sex", 5.8));
        arrayList.add(new CaloriesBurned("⛷ Skiing, snowboarding", 7));
        arrayList.add(new CaloriesBurned("\uD83D\uDCA4 Sleeping", 1));
        arrayList.add(new CaloriesBurned("\uD83E\uDDCD Standing", 1.5));
        arrayList.add(new CaloriesBurned("\uD83C\uDFCA Swimming", 8));
        arrayList.add(new CaloriesBurned("❤ Using cardiovascular equipment", 8));
        arrayList.add(new CaloriesBurned("✋ Volleyball", 5.5));
        arrayList.add(new CaloriesBurned("\uD83D\uDEB6 Walking", 3.8));
        arrayList.add(new CaloriesBurned("\uD83D\uDCFA Watching tv", 1));
        arrayList.add(new CaloriesBurned("\uD83D\uDC2C Water sports", 5.22));
        arrayList.add(new CaloriesBurned("\uD83D\uDCAA Weightlifting/strength training", 3));
        arrayList.add(new CaloriesBurned("\uD83D\uDC50 Wrestling", 6));
        arrayList.add(new CaloriesBurned("\uD83C\uDF07 Yoga", 3));
    }
}