package com.example.docapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class ListFragment extends Fragment {

    View view;
    private TextView orasTxt;
    ListView listView;

    String orasSearch, specSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list, container, false);
        Bundle bundle = this.getArguments();
        orasSearch = bundle.getString("oras");
        specSearch = bundle.getString("specialization");
        orasTxt = view.findViewById(R.id.orasTxt);
        orasTxt.setText(orasSearch + ", " + specSearch);

        String[] name = {"Dr. Ion", "MedLife", "BioClinica"};
        String[] specialization = {"Stomatologie", "General", "General"};
        String[] location = {"Pecica", "Arad", "Arad"};

        ArrayList<Doc> docArrayList = new ArrayList<>();

        for(int i = 0; i < name.length; i++){
            if(orasSearch.equals(location[i]) && (specSearch.equals("")|| specSearch.equals(specialization[i]) || specialization[i].equals("General"))){
                Doc doc = new Doc(name[i], specialization[i], location[i]);
                docArrayList.add(doc);
            }
        }

        ListAdapter listAdapter = new ListAdapter(getActivity(), docArrayList);

        listView = (ListView) view.findViewById(R.id.listview);

        listView.setAdapter(listAdapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DocFragment docFragment = new DocFragment();
                Bundle result = new Bundle();
                result.putString("name", docArrayList.get(position).getName());
                result.putString("specialization", docArrayList.get(position).getSpecialization());
                result.putString("location", docArrayList.get(position).getSpecialization());
                docFragment.setArguments(result);
                getFragmentManager().beginTransaction().replace(R.id.frame_layout, docFragment).commit();
            }
        });

        return view;
    }
}