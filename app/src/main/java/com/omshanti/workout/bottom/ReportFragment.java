package com.omshanti.workout.bottom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omshanti.workout.R;
import com.omshanti.workout.adapter.Report_DataAdapter;
import com.omshanti.workout.database.sqlite.DailyDatabaseHandler;
import com.omshanti.workout.database.sqlite.DailyReport;

import java.util.ArrayList;

public class ReportFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<DailyReport> arrayList;
    Report_DataAdapter adapter;
    DailyDatabaseHandler databaseHandler;

    public ReportFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        databaseHandler = new DailyDatabaseHandler(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.daily_database_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<DailyReport>();
        arrayList = databaseHandler.getAllData();
        adapter = new Report_DataAdapter(arrayList, getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }
}