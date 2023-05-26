package com.example.docapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SearchFragment extends Fragment {

    View view;
    private TextView orasTxt, specializationTxt;

    private Button nextBtn;

    String uname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_search, container, false);


        orasTxt = view.findViewById(R.id.cityTxt);
        specializationTxt = view.findViewById(R.id.specializationTxt);


        nextBtn = view.findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){



                /*
                oras = orasTxt.getText().toString();
                Intent intent = new Intent(getActivity(), ListActivity.class);
                intent.putExtra("oras", oras);
                startActivity(intent);

                 */

                if (orasTxt.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Enter a city", Toast.LENGTH_SHORT).show();
                }
                else {
                    ListFragment lf = new ListFragment();
                    Bundle result = new Bundle();
                    result.putString("oras", orasTxt.getText().toString());
                    result.putString("specialization", specializationTxt.getText().toString());
                    lf.setArguments(result);
                    getFragmentManager().beginTransaction().replace(R.id.frame_layout, lf).commit();
                    orasTxt.setText("");
                    specializationTxt.setText("");
                }


            }
        });
        return view;
    }
}