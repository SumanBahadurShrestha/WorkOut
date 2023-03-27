package com.omshanti.workout.filter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.omshanti.workout.R;
import com.omshanti.workout.adapter.ActivityLevelAdapter;
import com.omshanti.workout.model.ActivityLevel;

import java.util.ArrayList;

public class ActivityLevelFragment extends Fragment {
    ListView listView;
    ArrayList<ActivityLevel> arrayList;
    ActivityLevelAdapter levelAdapter;
    String selectedLevel = "";
    Button buttonNext;
    public ActivityLevelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activity_level, container, false);
        listView = (ListView) view.findViewById(R.id.Activity_level_list);
        buttonNext = (Button) view.findViewById(R.id.move_next);
        arrayList = new ArrayList<ActivityLevel>();
        arrayList.add(new ActivityLevel(R.drawable.level_sedentary, "Sedentary", false));
        arrayList.add(new ActivityLevel(R.drawable.level_lightly, "Lightly Active", false));
        arrayList.add(new ActivityLevel(R.drawable.level_moderately, "Moderately Active", false));
        arrayList.add(new ActivityLevel(R.drawable.level_very, "Very Active", false));
        levelAdapter = new ActivityLevelAdapter(getContext(), arrayList);
        listView.setAdapter(levelAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "Selected: " + arrayList.get(i).getTitle(), Toast.LENGTH_SHORT).show();
                selectedLevel =  arrayList.get(i).getTitle();
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedLevel == ""){
                    Toast.makeText(getContext(), "empty", Toast.LENGTH_SHORT).show();
                }else{
                    Fragment fragment = new WeightHeightFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });

        return view;
    }
}