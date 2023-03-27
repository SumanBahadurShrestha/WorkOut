package com.omshanti.workout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.omshanti.workout.R;
import com.omshanti.workout.model.SelectGoal;

import java.util.ArrayList;

public class MainGoalAdapter extends ArrayAdapter<SelectGoal> {
    public ArrayList<SelectGoal> arrayList;
    public Context mContext;

    public MainGoalAdapter( Context context, ArrayList<SelectGoal> arrylist) {
        super(context, 0, arrylist);
        this.mContext = context;
        this.arrayList = arrylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View data = convertView;
        if (convertView == null)
            data = LayoutInflater.from(mContext).inflate(R.layout.select_main_goal_item_container, null, false);
        TextView textViewTitle = (TextView) data.findViewById(R.id.goal_title);
        ImageView imageViewImage = (ImageView) data.findViewById(R.id.goal_img);
        SelectGoal selectGoal = arrayList.get(position);
        textViewTitle.setText(selectGoal.getTitle());
        imageViewImage.setImageResource(selectGoal.getImage());
        return data;
    }
}
