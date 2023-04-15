package com.omshanti.workout.filter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.omshanti.workout.R;
import com.omshanti.workout.adapter.BodypartAdapter;
import com.omshanti.workout.component.AppEnv;
import com.omshanti.workout.model.BodyPart;

import java.util.ArrayList;

public class BodyPartFragment extends Fragment {
    AppEnv appEnv;
    ListView listView;
    ArrayList<BodyPart> listItem;
    BodypartAdapter bodypartAdapter;
    String selecedGender;
    Button buttonNext;
    ArrayList<String> selectedPart;
    BodypartAdapter.ListviewItemClickListener listener;

    public BodyPartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_body_part, container, false);
        appEnv = (AppEnv) getActivity().getApplicationContext();
        listView = (ListView) view.findViewById(R.id.focus_area_list);
        buttonNext = (Button) view.findViewById(R.id.move_forward);
        listItem = new ArrayList<BodyPart>();
//        listItem = getResources().getStringArray(R.array.choose_male_body_part);
        listItem.add(new BodyPart("Back", R.drawable.exercise_back, false));
        listItem.add(new BodyPart("Chest", R.drawable.exercise_chest, false));
        listItem.add(new BodyPart("Arm", R.drawable.exercise_arm, false));
        listItem.add(new BodyPart("Leg", R.drawable.exercise_leg,false));
        listItem.add(new BodyPart("Shoulder", R.drawable.exercise_shoulder, false));

        //get selected gender
        selecedGender = "male";
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.bodypart_item_container, R.id.body_part_title, listItem);
        listener = new BodypartAdapter.ListviewItemClickListener() {
            @Override
            public void onCLick(View view, int position) {
                BodyPart bodyPart = listItem.get(position);
                if (view.getId() == R.id.body_part_title){
                    System.out.println(bodyPart.getSelected());
                    if (bodyPart.getSelected()){
                        bodyPart.setSelected(false);
                    }else {
                        bodyPart.setSelected(true);
                    }
                    bodypartAdapter.notifyDataSetChanged();
                }
            }
        };
        bodypartAdapter = new BodypartAdapter(getContext(), listItem, listener);
        listView.setAdapter(bodypartAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BodyPart bodyPart = listItem.get(i);
                Toast.makeText(getContext(), "Clicked at positon = " + bodyPart.getTitle(), Toast.LENGTH_SHORT).show();
                if (bodyPart.getSelected())
                    bodyPart.setSelected(false);
                else
                    bodyPart.setSelected(true);
                bodypartAdapter.notifyDataSetChanged();
            }
        });
        selectedPart = new ArrayList<String>();
        //shareperferance
        ArrayList<String> arrayList = appEnv.sharePerference.getBodyPart();
        if (arrayList != null){
            startMainActivity();
        }
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPart.clear();
                for(int i = 0; i < listItem.size(); i++){
                    BodyPart bodyPart = listItem.get(i);
                    if (bodyPart.getSelected()){
                        selectedPart.add(bodyPart.getTitle());
                    }
                }
                appEnv.sharePerference.setBodyPart(selectedPart);
                Toast.makeText(getContext(), "selected: "+selectedPart.size() + selectedPart , Toast.LENGTH_SHORT).show();
                Fragment fragment = new MainGoalFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

    private void startMainActivity() {
        MainGoalFragment fragment = new MainGoalFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment, "findFragment")
                .addToBackStack(null)
                .commit();
    }
}