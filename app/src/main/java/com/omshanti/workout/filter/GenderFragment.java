package com.omshanti.workout.filter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.omshanti.workout.FilterContainerActivity;
import com.omshanti.workout.R;
import com.omshanti.workout.WelcomScreenActivity;
import com.omshanti.workout.component.AppEnv;

public class GenderFragment extends Fragment {
    private CardView cardViewMale, cardViewFemale;
    String selecedGender = "";
    Button buttonNext;
    AppEnv appEnv;

    public GenderFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gender, container, false);
        appEnv = (AppEnv) getActivity().getApplicationContext();
        cardViewMale = (CardView) view.findViewById(R.id.male_Cardview);
        cardViewFemale = (CardView) view.findViewById(R.id.female_Cardview);
        buttonNext = (Button) view.findViewById(R.id.moveToNext);
        String getselectedGender = appEnv.sharePerference.getGender();
        if (!getselectedGender.equals("")){
            startMainActivity();
        }
//        if (getselectedGender.equals("female")){
//            selecedGender = "female";
//            cardViewMale.setAlpha(0.5f);
//            cardViewFemale.setAlpha(1f);
//            cardViewFemale.setCardElevation(5f);
//            showButton();
//        } else if (getselectedGender.equals("male")){
//            selecedGender = "male";
//            cardViewFemale.setAlpha(0.5f);
//            cardViewMale.setAlpha(1);
//            cardViewMale.setCardElevation(5f);
//            showButton();
//        }else{
//            selecedGender = "";
//        }

        cardViewFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecedGender = "female";
                cardViewMale.setAlpha(0.5f);
                cardViewFemale.setAlpha(1f);
                cardViewFemale.setCardElevation(5f);
                showButton();
            }
        });
        cardViewMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecedGender = "male";
                cardViewFemale.setAlpha(0.5f);
                cardViewMale.setAlpha(1);
                cardViewMale.setCardElevation(5f);
                showButton();
            }
        });

        return view;
    }
    private void startMainActivity() {
        WorkoutTypeFragment fragment = new WorkoutTypeFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment, "findFragment")
                .addToBackStack(null)
                .commit();
    }

    private void showButton() {
        if (!selecedGender.isEmpty()){
            buttonNext.setVisibility(View.VISIBLE);
            appEnv.sharePerference.setGender(selecedGender);
        }else{
            Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
        }

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new WorkoutTypeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
    }
}