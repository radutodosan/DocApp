package com.example.docapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.docapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class ListAdapter2 extends ArrayAdapter<Appointment> {



    public ListAdapter2(Context context, ArrayList<Appointment> appointmentArrayList){
        super(context, R.layout.list_item, appointmentArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Appointment appointment = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView docName = convertView.findViewById(R.id.clinicName);
        TextView info = convertView.findViewById(R.id.info);
        TextView date = convertView.findViewById(R.id.location);

        docName.setText(appointment.getDocName());
        info.setText(appointment.getSpecialization());
        date.setText(appointment.getDate());

        return convertView;
    }
}
