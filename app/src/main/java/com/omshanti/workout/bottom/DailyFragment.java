package com.omshanti.workout.bottom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.omshanti.workout.R;
import com.omshanti.workout.component.AppEnv;
import com.omshanti.workout.daily.BMIActivity;
import com.omshanti.workout.daily.BodyMeasureActivity;
import com.omshanti.workout.daily.StepActivity;
import com.omshanti.workout.daily.WaterActivity;

public class DailyFragment extends Fragment {
    AppEnv appEnv;
    Context mContext;
    CardView cardViewstep, cardViewwater, cardViewbmi, cardViewmeasure;
    Button buttonStep, buttonWater, buttonBmi, buttonBody;
    //hidden part
    TextView textViewShowStep;
    ProgressBar progressBarStep;
    public DailyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        mContext = getContext();
        appEnv = (AppEnv) getActivity().getApplicationContext();
        cardViewstep = (CardView) view.findViewById(R.id.cardView_step_count);
        cardViewwater = (CardView) view.findViewById(R.id.cardView_water_intake);
        cardViewbmi = (CardView) view.findViewById(R.id.cardView_bmi);
        cardViewmeasure = (CardView) view.findViewById(R.id.cardView_body_measure);
        buttonStep = (Button) view.findViewById(R.id.set_step);
        buttonWater = (Button) view.findViewById(R.id.set_water_intake);
        buttonBmi = (Button) view.findViewById(R.id.calculate_bmi);
        buttonBody = (Button) view.findViewById(R.id.measure_body);
        textViewShowStep = (TextView) view.findViewById(R.id.show_total_step);
        progressBarStep = (ProgressBar) view.findViewById(R.id.progress_bar);
        //get value
        boolean getstepvalue = appEnv.sharePerference.getStep();
        if (getstepvalue) {
            buttonStep.setVisibility(View.VISIBLE);
            textViewShowStep.setVisibility(View.GONE);
            progressBarStep.setVisibility(View.GONE);
        }
        else{
            buttonStep.setVisibility(View.GONE);
            textViewShowStep.setVisibility(View.VISIBLE);
            progressBarStep.setVisibility(View.VISIBLE);
        }
        buttonStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StepActivity.class);
                intent.putExtra("setStep", getstepvalue);
                startActivity(intent);
            }
        });
        buttonWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WaterActivity.class);
                intent.putExtra("setWater", true);
                startActivity(intent);
            }
        });
        buttonBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BMIActivity.class);
                intent.putExtra("calculateBmi", true);
                startActivity(intent);
            }
        });
        buttonBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BodyMeasureActivity.class);
                intent.putExtra("measureBody", true);
                startActivity(intent);
            }
        });
        cardViewstep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), StepActivity.class));
            }
        });
        cardViewbmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BMIActivity.class));
            }
        });
        cardViewmeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BodyMeasureActivity.class));
            }
        });
        cardViewwater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), WaterActivity.class));
            }
        });
        return view;
    }
}