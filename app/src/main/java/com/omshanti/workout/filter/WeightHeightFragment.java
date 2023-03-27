package com.omshanti.workout.filter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.omshanti.workout.MainActivity;
import com.omshanti.workout.R;

public class WeightHeightFragment extends Fragment {

    RadioButton radioButtonKg, radioButtonLb, radioButtonmt, radioButtonft;
    RadioGroup radioGroupWeight, radioGroupHeight;
    TextInputLayout textInputLayout_weight, textInputLayout_height1, textInputLayout_height2;
    Button buttoNext;
    public WeightHeightFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weight_height, container, false);
        textInputLayout_weight = (TextInputLayout) view.findViewById(R.id.weightInputLayout);
        textInputLayout_height1 = (TextInputLayout) view.findViewById(R.id.heightInputLayout1);
        textInputLayout_height2 = (TextInputLayout) view.findViewById(R.id.heightInputLayout2);
        radioButtonKg = (RadioButton) view.findViewById(R.id.kg);
        radioButtonLb = (RadioButton) view.findViewById(R.id.lb);
        radioButtonmt = (RadioButton) view.findViewById(R.id.mt);
        radioButtonft = (RadioButton) view.findViewById(R.id.ft);
        radioGroupWeight = (RadioGroup) view.findViewById(R.id.selecterHolder);
        radioGroupHeight = (RadioGroup) view.findViewById(R.id.selectorHolder);
        buttoNext = (Button) view.findViewById(R.id.final_move);
        if (radioButtonKg.isChecked()) {
            radioButtonKg.setTextColor(Color.WHITE);
            textInputLayout_weight.setHint("kg");
        }
        if (radioButtonmt.isChecked()) {
            radioButtonmt.setTextColor(Color.WHITE);
            textInputLayout_height1.setHint("meter");
        }
        radioGroupWeight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.kg) { //kg
                    radioButtonKg.setTextColor(Color.WHITE);
                    radioButtonLb.setTextColor(Color.BLACK);
                    textInputLayout_weight.setHint("kg");
                }
                else if (i == R.id.lb) { //lb
                    radioButtonKg.setTextColor(Color.BLACK);
                    radioButtonLb.setTextColor(Color.WHITE);
                    textInputLayout_weight.setHint("lb");
                }
                else
                    Toast.makeText(getContext(), "look at radio group click", Toast.LENGTH_SHORT).show();
            }
        });
        radioGroupHeight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.ft){
                    textInputLayout_height2.setVisibility(View.VISIBLE);
                    radioButtonft.setTextColor(Color.WHITE);
                    radioButtonmt.setTextColor(Color.BLACK);
                    textInputLayout_height1.setHint("ft");
                    textInputLayout_height2.setHint("inch");
                }
                else if (i == R.id.mt){
                    textInputLayout_height2.setVisibility(View.GONE);
                    radioButtonft.setTextColor(Color.BLACK);
                    radioButtonmt.setTextColor(Color.WHITE);
                    textInputLayout_height1.setHint("meter");
                }
            }
        });

        buttoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}