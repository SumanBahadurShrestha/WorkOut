package com.omshanti.workout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.omshanti.workout.R;
import com.omshanti.workout.model.TChallenge;

import java.util.ArrayList;

public class TrainingChallengeAdapter extends ArrayAdapter<TChallenge> {
    Context mContext;
    ArrayList<TChallenge> arrayList;

    public TrainingChallengeAdapter(Context context, ArrayList<TChallenge> arraylist) {
        super(context, 0, arraylist);
        this.mContext = context;
        this.arrayList = arraylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View data = convertView;
        TChallenge tChallenge = arrayList.get(position);
        if (data == null){
            data = LayoutInflater.from(mContext).inflate(R.layout.training_challenge_card, null, false);
        }

        return data;
    }
}
