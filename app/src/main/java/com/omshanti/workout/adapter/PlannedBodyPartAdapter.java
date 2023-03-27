package com.omshanti.workout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.omshanti.workout.R;

public class PlannedBodyPartAdapter extends RecyclerView.Adapter<PlannedBodyPartAdapter.ViewHolder> {
    private Context mContext;

    public PlannedBodyPartAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.selected_workout_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewTitle.setText(String.format("Row number%d", position));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView textViewPercentage, textViewTitle, textViewWordoutDay;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardview_selected);
            imageView = (ImageView) itemView.findViewById(R.id.body_part_image);
            textViewPercentage = (TextView) itemView.findViewById(R.id.textview_finishedper);
            textViewTitle = (TextView) itemView.findViewById(R.id.body_part_title);
            textViewWordoutDay = (TextView) itemView.findViewById(R.id.count_exercise_day);
            button = (Button) itemView.findViewById(R.id.button_start);
        }
    }
}
