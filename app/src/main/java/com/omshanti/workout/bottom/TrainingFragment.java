package com.omshanti.workout.bottom;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.omshanti.workout.R;
import com.omshanti.workout.adapter.PartLevelAdapter;
import com.omshanti.workout.adapter.PlannedBodyPartAdapter;
import com.omshanti.workout.adapter.TrainingChallengeAdapter;
import com.omshanti.workout.component.ListViewHelper;
import com.omshanti.workout.model.PartLevel;
import com.omshanti.workout.model.TChallenge;

import java.util.ArrayList;
import java.util.Calendar;

public class TrainingFragment extends Fragment {
    Context mContext;
    ViewPager2 viewpager;
    PlannedBodyPartAdapter bodyPartAdapter;
    ListView listViewFixed;
    ArrayList<TChallenge> arrayList;
    TrainingChallengeAdapter challengeAdapter;
    //part
    ListView listViewChest, listViewLeg;
    PartLevelAdapter levelAdapter;
    ArrayList<PartLevel> partLevelArrayList;
    ArrayList<PartLevel> partLevelArrayListLeg;
    public TrainingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_training, container, false);
        mContext = getContext();
        viewpager = (ViewPager2) view.findViewById(R.id.viewPager2);
        listViewFixed = (ListView) view.findViewById(R.id.listView_fix_plan);
        listViewChest = (ListView) view.findViewById(R.id.listView_chest);
        listViewLeg = (ListView) view.findViewById(R.id.listView_leg);
        bodyPartAdapter = new PlannedBodyPartAdapter(mContext);
        viewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(bodyPartAdapter);
        float pageMargin= getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);
        viewpager.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float myOffset = position * -(2 * pageOffset + pageMargin);
                if (viewpager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                    if (ViewCompat.getLayoutDirection(viewpager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.setTranslationX(-myOffset);
                    } else {
                        page.setTranslationX(myOffset);
                    }
                } else {
                    page.setTranslationY(myOffset);
                }
            }
        });
        System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
        arrayList = new ArrayList<TChallenge>();
        arrayList.add(new TChallenge(R.drawable.exercise_back, "Full Body", "28", "0"));
        arrayList.add(new TChallenge(R.drawable.exercise_shoulder, "Lower Body", "28", "0"));
        challengeAdapter = new TrainingChallengeAdapter(mContext, arrayList);
        listViewFixed.setAdapter(challengeAdapter);
        ListViewHelper.getListViewSize(listViewFixed);

        partLevelArrayList = new ArrayList<PartLevel>();
        partLevelArrayList.add(new PartLevel(R.drawable.exercise_chest, R.drawable.ic_outline_bolt_beg_24,
                R.drawable.ic_outline_bolt_white_24, R.drawable.ic_outline_bolt_white_24, "Chest", "Beginner", "#B4FFA044"));
        partLevelArrayList.add(new PartLevel(R.drawable.exercise_chest, R.drawable.ic_outline_bolt_int_24,
                R.drawable.ic_outline_bolt_int_24, R.drawable.ic_outline_bolt_white_24, "Chest", "Intermediate", "#A8448FFF"));
        partLevelArrayList.add(new PartLevel(R.drawable.exercise_chest, R.drawable.ic_outline_bolt_adv_24,
                R.drawable.ic_outline_bolt_adv_24, R.drawable.ic_outline_bolt_adv_24, "Chest", "Advance", "#A4FF4444"));
        levelAdapter = new PartLevelAdapter(mContext, partLevelArrayList);
        listViewChest.setAdapter(levelAdapter);
        ListViewHelper.getListViewSize(listViewChest);

        partLevelArrayListLeg = new ArrayList<PartLevel>();
        partLevelArrayListLeg.add(new PartLevel(R.drawable.exercise_leg, R.drawable.ic_outline_bolt_beg_24,
                R.drawable.ic_outline_bolt_white_24, R.drawable.ic_outline_bolt_white_24, "Leg", "Beginner", "#B4FFA044"));
        partLevelArrayListLeg.add(new PartLevel(R.drawable.exercise_leg, R.drawable.ic_outline_bolt_int_24,
                R.drawable.ic_outline_bolt_int_24, R.drawable.ic_outline_bolt_white_24, "Leg", "Intermediate", "#A8448FFF"));
        partLevelArrayListLeg.add(new PartLevel(R.drawable.exercise_leg, R.drawable.ic_outline_bolt_adv_24,
                R.drawable.ic_outline_bolt_adv_24, R.drawable.ic_outline_bolt_adv_24, "Leg", "Advance", "#A4FF4444"));
        levelAdapter = new PartLevelAdapter(mContext, partLevelArrayListLeg);
        listViewLeg.setAdapter(levelAdapter);
        ListViewHelper.getListViewSize(listViewLeg);
        return view;
    }
}