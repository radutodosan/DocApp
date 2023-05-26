package com.example.docapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

public class CalendarFragment extends Fragment {

    View view;

    CalendarView calendarView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_calendar, container, false);

        Bundle bundle = this.getArguments();

        calendarView = (CalendarView) view.findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = i2 + "/" + (i1 + 1) + "/" + i;


                FormFragment formFragment = new FormFragment();

                Bundle result = new Bundle();


                String docname = bundle.getString("doc");
                result.putString("date", date);
                result.putString("doc", docname);
                formFragment.setArguments(result);

                getFragmentManager().beginTransaction().replace(R.id.frame_layout, formFragment).commit();
            }
        });

        return view;
    }
}