package com.omshanti.workout.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.omshanti.workout.R;
import com.omshanti.workout.model.PartLevel;

import java.util.ArrayList;

public class PartLevelAdapter extends ArrayAdapter<PartLevel> {
    Context mContext;
    ArrayList<PartLevel> arrayList;

    public PartLevelAdapter(Context context, ArrayList<PartLevel> arrayList) {
        super(context, 0, arrayList);
        this.mContext = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View data = convertView;
        PartLevel partLevel = arrayList.get(position);
        if (data == null)
            data = LayoutInflater.from(mContext).inflate(R.layout.body_part_level_card, null, false);
        ImageView imageViewBackground = (ImageView) data.findViewById(R.id.part_level_image);
        ImageView imageViewBeginner = (ImageView) data.findViewById(R.id.part_level_1);
        ImageView imageViewIntermediate = (ImageView) data.findViewById(R.id.part_level_2);
        ImageView imageViewAdvance = (ImageView) data.findViewById(R.id.part_level_3);
        TextView textViewPartTitle = (TextView) data.findViewById(R.id.textview_part_title);
        TextView textViewPartLevel = (TextView) data.findViewById(R.id.textview_part_level);
        imageViewBackground.setImageResource(partLevel.getImageViewBackground());
        imageViewBeginner.setImageResource(partLevel.getImageViewbeg());
        imageViewIntermediate.setImageResource(partLevel.getImageViewInt());
        imageViewAdvance.setImageResource(partLevel.getImageViewAdv());
        textViewPartTitle.setText(partLevel.getStringTitle());
        textViewPartLevel.setText(partLevel.getStringLevel());
        textViewPartLevel.setBackgroundColor(Color.parseColor(partLevel.getColor()));
        return data;
    }
}
