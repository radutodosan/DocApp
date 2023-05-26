package com.example.docapp;

import android.database.Cursor;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListFragment extends Fragment {

    View view;
    private TextView orasTxt;
    ListView listView;

    String orasSearch, specSearch, searchResults = "";

    Database myDB;
    ArrayList<String> doc_id, name, specialization, location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list, container, false);
        Bundle bundle = this.getArguments();
        orasSearch = bundle.getString("oras");
        specSearch = bundle.getString("specialization");
        orasTxt = view.findViewById(R.id.orasTxt);



        myDB = new Database(getActivity());
        doc_id = new ArrayList<>();
        name = new ArrayList<>();
        specialization = new ArrayList<>();
        location = new ArrayList<>();

        storeData();

        ArrayList<Doc> docArrayList = new ArrayList<>();

        for(int i = 0; i < name.size(); i++){
            if(searchCity(location.get(i), orasSearch) && (specSearch.equals("") || specSearch.equals(specialization.get(i)) || specialization.get(i).equals("General"))){
                Doc doc = new Doc(name.get(i), specialization.get(i), location.get(i));
                searchResults += location.get(i) + " ";
                docArrayList.add(doc);
            }

        }
        if(docArrayList.size() != 0){
            orasTxt.setText(searchResults);
        }
        else{
            orasTxt.setText("No matches found!");
            Toast.makeText(getActivity(), "No matches found!", Toast.LENGTH_SHORT).show();
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
                result.putString("location", docArrayList.get(position).getLocation());
                docFragment.setArguments(result);
                getFragmentManager().beginTransaction().replace(R.id.frame_layout, docFragment).commit();
            }
        });

        return view;
    }

    void storeData(){
        Cursor cursor = myDB.readAllData();

        if(cursor.getCount() == 0){
            Toast.makeText(getActivity(), "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                doc_id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                specialization.add(cursor.getString(2));
                location.add(cursor.getString(3));
            }

        }
    }

    public static boolean searchCity(String input, String substring) {
        // Escape special characters in the substring
        String escapedSubstring = Pattern.quote(substring);

        // Create the regular expression pattern with case-insensitive flag
        Pattern pattern = Pattern.compile(escapedSubstring, Pattern.CASE_INSENSITIVE);

        // Match the pattern against the input string
        Matcher matcher = pattern.matcher(input);

        // Return true if a match is found, false otherwise
        return matcher.find();
    }
}