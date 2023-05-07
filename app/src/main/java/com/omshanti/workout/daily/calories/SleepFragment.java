package com.omshanti.workout.daily.calories;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.omshanti.workout.R;

import java.util.ArrayList;

public class SleepFragment extends Fragment {

    EditText editTextFallSleep;
    Spinner spinnerWakeTime;
    MaterialButton materialButton;
    Spinner spinnerRoutine;
    TextView textViewRisk;
    TextView nine, seven, six, four, three, one;

    public SleepFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sleep, container, false);

        editTextFallSleep = (EditText) view.findViewById(R.id.ed_fall_sleep);
        spinnerWakeTime = (Spinner) view.findViewById(R.id.sp_getUp_time);
        materialButton = (MaterialButton) view.findViewById(R.id.bt_Calculate);
        spinnerRoutine = (Spinner) view.findViewById(R.id.spin_routine);
        textViewRisk = (TextView) view.findViewById(R.id.risk_value);
        nine = (TextView) view.findViewById(R.id.ninehrs);
        seven = (TextView) view.findViewById(R.id.sevenhrs);
        six = (TextView) view.findViewById(R.id.sixhrs);
        four = (TextView) view.findViewById(R.id.fourhrs);
        three = (TextView) view.findViewById(R.id.threehrs);
        one = (TextView) view.findViewById(R.id.onehrs);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aSleep = editTextFallSleep.getText().toString().trim();
                if (aSleep.equals("")) {
                    Toast.makeText(getContext(), "Empty Vlaue", Toast.LENGTH_SHORT).show();
                    return;
                }
                String wakeUp = spinnerWakeTime.getSelectedItem().toString();
                String[] wake = wakeUp.split(":");
                String[] breakup = wake[1].split(" ");
                System.out.println(wake[0] + "/" + breakup[0]);
                int asleep = Integer.parseInt(aSleep);
                int wakeH = Integer.parseInt(wake[0]);
                int wakeM = Integer.parseInt(breakup[0]);
                String morning;//AM
                int[] arrayList = {9,7,6,4,3,1};
                int[] arraylist = {0,30,0,30,0,30};
                int finalH, finalM;
                for (int i = 0; i < arrayList.length; i++){
                    if (wakeH <= arrayList[i]){//6 < 9
                        morning = breakup[1];
                        finalH = ((wakeH + 12) - arrayList[i]);
                        if (wakeM <= arraylist[i]) {
                            finalH = ((wakeH + 10) - arrayList[i]) + 1;
                            finalM = ((wakeM + 60) - arraylist[i]) - asleep;
                        }
                        else {
                            finalM = wakeM - arraylist[i] - asleep;
                        }
                        if (morning.equals("AM") && finalH < 12)
                            morning = "PM";
                        else
                            morning = "AM";
//                        System.out.println("H " + finalH+":"+finalM + morning);
                    }else{ //4 > 3
                        //do nothing
                        finalH = wakeH - arrayList[i];
                        morning = breakup[1];
                        if (wakeM <= arraylist[i]) {
                            finalH = wakeH - arrayList[i] - 1;
                            if (finalH == 0)
                                finalH = 12;
                            finalM = ((wakeM + 60) - arraylist[i]) - asleep;
                        }
                        else
                            finalM = wakeM - arraylist[i] - asleep;
                        if (morning.equals("PM") && wakeH == 12)
                            morning = "AM";
//                        System.out.println("S " + finalH+":"+finalM + morning);
                    }
                    System.out.println("Save" + finalH+":"+finalM + morning);
                    if (arrayList[i] == 9)
                        nine.setText("• "+ finalH+":"+finalM +" "+ morning + "(6 cycles, 9h of sleep) - recommended for long-sleepers,");
                    if (arrayList[i] == 7)
                        seven.setText("♥ "+ finalH+":"+finalM +" "+ morning + "(5 cycles, 7h30m of sleep) - recommended for average-sleepers,");
                    if (arrayList[i] == 6)
                        six.setText("• "+ finalH+":"+finalM +" "+ morning + "(4 cycles, 6h of sleep) - recommended for short-sleepers,");
                    if (arrayList[i] == 4)
                        four.setText("• "+ finalH+":"+finalM +" "+ morning + "(3 cycles, 4h30m of sleep),");
                    if (arrayList[i] == 3)
                        three.setText("• "+ finalH+":"+finalM +" "+ morning + "(2 cycles, 3h of sleep),");
                    if (arrayList[i] == 1)
                        one.setText("• "+ finalH+":"+finalM +" "+ morning + "(1 cycle, 1h30m of sleep).");
                }
//                int finalH = ((wakeH + 10) - 9) + 2;
//                int finalM = wakeM - asleep;
//                System.out.println(finalH+":"+finalM);
            }
        });

        spinnerRoutine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String routine = spinnerRoutine.getSelectedItem().toString();
                if (routine.equals("4 hours"))
                    textViewRisk.setText("23 %");
                else if (routine.equals("5 hours"))
                    textViewRisk.setText("14 %");
                else if (routine.equals("6 hours"))
                    textViewRisk.setText("5 %");
                else if (routine.equals("7 hours"))
                    textViewRisk.setText("0 %");
                else if (routine.equals("8 hours"))
                    textViewRisk.setText("4 %");
                else if (routine.equals("9 hours"))
                    textViewRisk.setText("11 %");
                else if (routine.equals("10 hours"))
                    textViewRisk.setText("19 %");
                else
                    textViewRisk.setText("28 %");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        return view;
    }
}