package com.example.docapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class DocFragment extends Fragment {

    View view;

    TextView nameTxt, specializationTxt, locationTxt;

    Button appBtn;
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
        appBtn = view.findViewById(R.id.appointmentBtn);

        nameTxt.setText(name);
        specializationTxt.setText(specialization);
        locationTxt.setText(location);

        appBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);

                if(sharedPreferences.getString("username", null) == null){
                    Toast.makeText(getActivity(), "You must setup your username!", Toast.LENGTH_SHORT).show();
                }
                else{
                    FormFragment lf = new FormFragment();
                    Bundle result = new Bundle();
                    result.putString("doc", name);
                    lf.setArguments(result);
                    getFragmentManager().beginTransaction().replace(R.id.frame_layout, lf).commit();
                }

            }
        });


        return view;
    }
}