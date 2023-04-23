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

public class ListAdapter extends ArrayAdapter<Doc> {



    public ListAdapter(Context context, ArrayList<Doc> docArrayList){
        super(context, R.layout.list_item, docArrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Doc doc = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView docName = convertView.findViewById(R.id.clinicName);
        TextView info = convertView.findViewById(R.id.info);
        TextView location = convertView.findViewById(R.id.location);

        docName.setText(doc.getName());
        info.setText(doc.getSpecialization());
        location.setText(doc.getLocation());

        return convertView;
    }
}
