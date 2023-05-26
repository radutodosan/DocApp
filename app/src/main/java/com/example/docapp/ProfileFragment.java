package com.example.docapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    View view;

    TextView usernameTxt;
    EditText fnameInput, numberInput, emailInput;
    String username, fname, number, email;
    Button loginBtn, saveBtn;

    Database myDB;

    ArrayList<String> users_id, users;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        myDB = new Database(getActivity());
        users_id = new ArrayList<>();
        users = new ArrayList<>();

        usernameTxt = view.findViewById(R.id.usernameTxt);
        fnameInput = view.findViewById(R.id.fnameTxt);
        numberInput = view.findViewById(R.id.numberTxt);
        emailInput = view.findViewById(R.id.emailTxt);

        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);

        usernameTxt.setText("Username: " + sharedPreferences.getString("username", null));
        fnameInput.setText(sharedPreferences.getString("fname", null));
        numberInput.setText(sharedPreferences.getString("number", null));
        emailInput.setText(sharedPreferences.getString("email", null));

        loginBtn = view.findViewById(R.id.loginBtn);
        username = sharedPreferences.getString("username", null);
        if(username == null){
            loginBtn.setText("LOGIN");
        }
        else{
            loginBtn.setText("LOGOUT");
        }
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginFragment lf = new LoginFragment();
                getFragmentManager().beginTransaction().replace(R.id.frame_layout, lf).commit();
            }
        });

        saveBtn = view.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameTxt.getText().toString();
                fname = fnameInput.getText().toString();
                number = numberInput.getText().toString();
                email = emailInput.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("fname", fname);
                editor.putString("number", number);
                editor.putString("email", email);
                editor.commit();
            }
        });

        return view;

    }


}