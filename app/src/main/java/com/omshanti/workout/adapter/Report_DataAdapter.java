package com.omshanti.workout.adapter;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.omshanti.workout.R;
import com.omshanti.workout.database.sqlite.DailyReport;

import java.util.ArrayList;

public class Report_DataAdapter extends RecyclerView.Adapter<Report_DataAdapter.ViewHolder> {
    ArrayList<DailyReport> arrayList;
    Context mContext;
    int lastPosition = 1;

    public Report_DataAdapter(ArrayList<DailyReport> arrayList, Context mContext) {
        this.arrayList = arrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.daily_data_list, parent, false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DailyReport dailyReport = arrayList.get(position);
        holder.card.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position == 0) ? R.anim.testupbottom: R.anim.testbottomup);
        animation.setRepeatCount(0);
        holder.textViewId.setText(String.valueOf(dailyReport.getId()));
        holder.card.setAnimation(animation);
//        lastPosition = position;
        holder.textViewDate.setText(dailyReport.getDate());
        holder.textViewwater.setText(String.valueOf(dailyReport.getWaterTarget()));
        holder.textViewwalk.setText(String.valueOf(dailyReport.getWalkTarget()));
        holder.textViewwatered.setText(String.valueOf(dailyReport.getWatered()));
        holder.textViewwaled.setText(String.valueOf(dailyReport.getWalked()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView textViewId, textViewDate, textViewwater, textViewwatered, textViewwalk, textViewwaled;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = (CardView) itemView.findViewById(R.id.cardview);
            textViewId = (TextView) itemView.findViewById(R.id.tv_data_id);
            textViewDate = (TextView) itemView.findViewById(R.id.tv_data_date);
            textViewwater = (TextView) itemView.findViewById(R.id.tv_data_water);
            textViewwatered = (TextView) itemView.findViewById(R.id.tv_data_watered);
            textViewwalk = (TextView) itemView.findViewById(R.id.tv_data_walk);
            textViewwaled = (TextView) itemView.findViewById(R.id.tv_data_walked);
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }
}
