package com.example.docapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginFragment extends Fragment {

    View view;

    Database myDB;
    EditText usernameInput,passwordInput;
    String username, password;

    Button loginBtn, signupBtn;

    ArrayList<String> users_id, users, passwords;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        usernameInput = view.findViewById(R.id.usernameTxt);
        passwordInput = view.findViewById(R.id.passwordTxt);

        loginBtn = view.findViewById(R.id.loginBtn);
        signupBtn = view.findViewById(R.id.signupBtn);

        myDB = new Database(getActivity());

        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();

                if(username.equals("") || password.equals("")){
                    Toast.makeText(getActivity(), "Please enter the information.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass = myDB.checkusernamepassword(username,password);
                    if(checkuserpass == true){
                        SearchFragment lf = new SearchFragment();

                        editor.putString("username", username);
                        editor.commit();

                        getFragmentManager().beginTransaction().replace(R.id.frame_layout, lf).commit();
                    }
                    else{
                        Toast.makeText(getActivity(), "This account doesn't exist. Sign up!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();

                if(username.equals("") || password.equals("")){
                    Toast.makeText(getActivity(), "Please enter the information.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuser = myDB.checkusername(username);
                    if(checkuser == false){
                        myDB.addUser(username, password);

                        SearchFragment lf = new SearchFragment();

                        editor.putString("username", username);
                        editor.commit();

                        getFragmentManager().beginTransaction().replace(R.id.frame_layout, lf).commit();

                    }
                    else{
                        Toast.makeText(getActivity(), "This username already exists!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

}