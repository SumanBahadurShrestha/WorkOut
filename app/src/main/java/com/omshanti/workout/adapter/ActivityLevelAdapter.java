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
import com.omshanti.workout.model.ActivityLevel;

import java.util.ArrayList;

public class ActivityLevelAdapter extends ArrayAdapter<ActivityLevel> {
    Context mContext;
    ArrayList<ActivityLevel> arrayList;

    public ActivityLevelAdapter(Context context, ArrayList<ActivityLevel> activityLevelArrayList) {
        super(context, 0, activityLevelArrayList);
        this.mContext = context;
        this.arrayList = activityLevelArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View data = convertView;
        ActivityLevel activityLevel = arrayList.get(position);
        if (convertView == null)
            data = LayoutInflater.from(mContext).inflate(R.layout.select_body_part_item_container, null, false);
        TextView textView = data.findViewById(R.id.body_part_title);
        ImageView imageView = data.findViewById(R.id.body_part_image);
        textView.setText(activityLevel.getTitle());
        imageView.setImageResource(activityLevel.getImage());
        return data;
    }
}
