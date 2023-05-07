package com.omshanti.workout.daily;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.omshanti.workout.R;

public class BodyMeasureActivity extends AppCompatActivity {

    EditText editTextBust, editTextWaist, editTextHighHip, editTextHips;
    MaterialButton materialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_measure);
        editTextBust = (EditText) findViewById(R.id.ed_bust);
        editTextWaist = (EditText) findViewById(R.id.ed_waist);
        editTextHighHip = (EditText) findViewById(R.id.ed_highhip);
        editTextHips = (EditText) findViewById(R.id.ed_hips);
        materialButton = (MaterialButton) findViewById(R.id.button_calculate);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bust = editTextBust.getText().toString().trim();
                String waist = editTextWaist.getText().toString().trim();
                String highHip = editTextHighHip.getText().toString().trim();
                String hips = editTextHips.getText().toString().trim();
                if (bust.equals("") || waist.equals("") || highHip.equals("") || hips.equals("")) {
                    Toast.makeText(BodyMeasureActivity.this, "empty value", Toast.LENGTH_SHORT).show();
                    return;
                }
                int intBust = Integer.parseInt(bust);
                int intWaist = Integer.parseInt(waist);
                int intHighHip = Integer.parseInt(highHip);
                int intHips = Integer.parseInt(hips);
                if ((intBust - intHips) <= 1 && (intHips - intBust) < 3.6 && (intBust - intWaist) >= 9 || (intHips - intWaist) >= 10)
                    Toast.makeText(BodyMeasureActivity.this, "Hourglass", Toast.LENGTH_SHORT).show();
                if ((intHips - intBust) >= 3.6 && (intHips - intBust) < 10 && (intHips - intWaist) >= 9 && (intHighHip / intWaist) < 1.193)
                    Toast.makeText(BodyMeasureActivity.this, "Bottom hourglass", Toast.LENGTH_SHORT).show();
                if ((intBust - intHips) > 1 && (intBust - intHips) < 10 && (intBust - intWaist) >= 9)
                    Toast.makeText(BodyMeasureActivity.this, "Top hourglass", Toast.LENGTH_SHORT).show();
                if ((intHips - intBust) > 2 && (intHips - intWaist) >= 7 && (intHighHip / intWaist) >= 1.193)
                    Toast.makeText(BodyMeasureActivity.this, "Spoon", Toast.LENGTH_SHORT).show();
                if ((intHips - intBust) >= 3.6 && (intHips - intWaist) < 9)
                    Toast.makeText(BodyMeasureActivity.this, "Triangle", Toast.LENGTH_SHORT).show();
                if ((intBust - intHips) >= 3.6 && (intBust - intWaist) < 9)
                    Toast.makeText(BodyMeasureActivity.this, "Inverted triangle", Toast.LENGTH_SHORT).show();
                if ((intHips - intBust) < 3.6 && (intBust - intHips) < 3.6 && (intBust - intWaist) < 9 && (intHips - intWaist) < 10)
                    Toast.makeText(BodyMeasureActivity.this, "Rectangle", Toast.LENGTH_SHORT).show();
            }
        });
    }
}