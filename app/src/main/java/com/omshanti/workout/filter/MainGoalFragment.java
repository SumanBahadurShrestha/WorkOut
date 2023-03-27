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
import com.omshanti.workout.adapter.MainGoalAdapter;
import com.omshanti.workout.model.SelectGoal;

import java.util.ArrayList;

public class MainGoalFragment extends Fragment {
    ListView listView;
    ArrayList<SelectGoal> arrayList;
    MainGoalAdapter adapter;
    String selecedOne = "";
    Button buttonNext;
    public MainGoalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_goal, container, false);
        arrayList = new ArrayList<SelectGoal>();
        arrayList.add(new SelectGoal("Love", R.drawable.dummy_weight_loss));
        arrayList.add(new SelectGoal("Hate", R.drawable.dummy_weight_loss));
        arrayList.add(new SelectGoal("Feel", R.drawable.dummy_weight_loss));
        arrayList.add(new SelectGoal("Test", R.drawable.dummy_weight_loss));
        listView = (ListView) view.findViewById(R.id.goals_listview);
        buttonNext = (Button) view.findViewById(R.id.move_ahead);
        adapter = new MainGoalAdapter(getContext(), arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String selected = arrayList.get(i);
                listView.setItemChecked(i, true);
                listView.setSelection(i);
                selecedOne = arrayList.get(i).getTitle();
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecedOne==""){
                    Toast.makeText(getContext(), "select one", Toast.LENGTH_SHORT).show();
                }else {
                    Fragment fragment = new ActivityLevelFragment();
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