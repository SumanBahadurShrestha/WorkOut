package com.omshanti.workout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.omshanti.workout.R;
import com.omshanti.workout.model.BodyPart;

import java.util.ArrayList;

public class BodypartAdapter extends BaseAdapter{
    ArrayList<BodyPart> arrayList;
    Context context;
    BodypartAdapter.ListviewItemClickListener itemClickListener;

    public interface ListviewItemClickListener{
        void onCLick(View view, int position);
    }

    public BodypartAdapter(Context mContext, ArrayList<BodyPart> arrayList, ListviewItemClickListener listener) {
        this.arrayList = arrayList;
        this.context = mContext;
        this.itemClickListener = listener;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private int lastPosition = -1;
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BodyPart bodyPart = arrayList.get(i);
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.select_body_part_item_container, viewGroup, false);
        }
        TextView textView = view.findViewById(R.id.body_part_title);
        ImageView imageView = view.findViewById(R.id.body_part_image);
        CheckBox checkBox = view.findViewById(R.id.body_part_checkbox);

//        Animation animation = AnimationUtils.loadAnimation(context, (i > lastPosition)? R.anim.upfrombottom: R.anim.downfromtop);
//        view.startAnimation(animation);
        lastPosition = i;
        if (bodyPart.getSelected()){
            checkBox.setVisibility(View.VISIBLE);
        }else{
            checkBox.setVisibility(View.GONE);
        }
        textView.setText(bodyPart.getTitle());
        imageView.setImageResource(bodyPart.getImage());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onCLick(view, i);
            }
        });
        return view;
    }
}
