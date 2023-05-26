package com.example.docapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AppointmentsFragment extends Fragment {
    View view;

    Database myDB;

    ListView listView;
    String username;
    ArrayList<String> app_id, docname, specialization, date;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_appointments, container, false);

        myDB = new Database(getActivity());

        app_id = new ArrayList<>();
        docname = new ArrayList<>();
        specialization = new ArrayList<>();
        date = new ArrayList<>();

        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);

        username = sharedPreferences.getString("username", null);

        storeData();


        ArrayList<Appointment> appointmentArrayList = new ArrayList<>();

        for(int i = 0; i < docname.size(); i++){
                Appointment appointment = new Appointment(docname.get(i), specialization.get(i), date.get(i));
                appointmentArrayList.add(appointment);

        }

        ListAdapter2 listAdapter = new ListAdapter2(getActivity(), appointmentArrayList);

        listView = (ListView) view.findViewById(R.id.listview);

        listView.setAdapter(listAdapter);

        return view;
    }

    void storeData(){
        Cursor cursor = myDB.readAllDataAppointments(username);

        if(cursor.getCount() == 0){
            Toast.makeText(getActivity(), "No appointments.", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                app_id.add(cursor.getString(0));
                docname.add(cursor.getString(2));
                specialization.add(cursor.getString(5));
                date.add(cursor.getString(3) + ", " + cursor.getString(4));
            }

        }
    }
}