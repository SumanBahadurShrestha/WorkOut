package com.omshanti.workout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
        ImageView imageView = (ImageView) data.findViewById(R.id.img_chall_Background);
        TextView textViewTitle = (TextView) data.findViewById(R.id.tv_chall_Title);
        TextView textViewDaysRemain = (TextView) data.findViewById(R.id.tv_days_remain);
        TextView textViewCompletedPer = (TextView) data.findViewById(R.id.tv_per_complete);
        ProgressBar progressBar = (ProgressBar) data.findViewById(R.id.progressBarH);
        imageView.setImageResource(tChallenge.getImage());
        textViewTitle.setText(tChallenge.getTitle());
        textViewDaysRemain.setText(tChallenge.getDayleft() + " days left");
        textViewCompletedPer.setText(tChallenge.getPercompleted() + "%");
        progressBar.setMax(28);
        progressBar.setProgress(0);

        return data;
    }
}
