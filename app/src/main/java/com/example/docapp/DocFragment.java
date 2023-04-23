package com.example.docapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


public class DocFragment extends Fragment {

    View view;

    TextView nameTxt, specializationTxt, locationTxt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_doc, container, false);

        Bundle bundle = this.getArguments();

        String name = bundle.getString("name");
        String specialization = bundle.getString("specialization");
        String location = bundle.getString("location");

        nameTxt = (TextView) view.findViewById(R.id.name_profile);
        specializationTxt = (TextView) view.findViewById(R.id.specialization_profile);
        locationTxt = (TextView) view.findViewById(R.id.location_profile);

        nameTxt.setText(name);
        specializationTxt.setText(specialization);
        locationTxt.setText(location);

        return view;
    }
}