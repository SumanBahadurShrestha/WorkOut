package com.omshanti.workout.filter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.omshanti.workout.R;
import com.omshanti.workout.component.AppEnv;

public class WorkoutTypeFragment extends Fragment {
    ImageView imageViewHome, imageViewGym;
    RelativeLayout relativeLayoutHome, relativeLayoutGym;
    Fragment fragment;
    AppEnv appEnv;

    public WorkoutTypeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout_type, container, false);
        appEnv = (AppEnv) getActivity().getApplicationContext();
        imageViewGym = (ImageView) view.findViewById(R.id.workout_gym);
        relativeLayoutGym = (RelativeLayout) view.findViewById(R.id.relative_gym_workout);
        imageViewHome = (ImageView) view.findViewById(R.id.workout_home);
        relativeLayoutHome = (RelativeLayout) view.findViewById(R.id.relative_home_workout);

        Glide.with(getContext())
                .load(R.drawable.nepal_flag)
                .into(imageViewHome);
        Glide.with(getContext())
                .load(R.drawable.nepal_flag)
                .into(imageViewGym);

        String getworkType = appEnv.sharePerference.getWorkoutType();
        if (!getworkType.equals("")){
            startMainActivity();
        }
        relativeLayoutHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "selected Home", Toast.LENGTH_SHORT).show();
                String txt = "Home";
                appEnv.sharePerference.setWorkoutType(txt);
                fragment = new BodyPartFragment();
                setFragment(fragment);
            }
        });
        relativeLayoutGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "selected Gym", Toast.LENGTH_SHORT).show();
                String txt = "Gym";
                appEnv.sharePerference.setWorkoutType(txt);
                fragment = new BodyPartFragment();
                setFragment(fragment);
            }
        });
        return view;
    }

    private void startMainActivity() {
        BodyPartFragment fragment = new BodyPartFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment, "findFragment")
                .addToBackStack(null)
                .commit();
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}