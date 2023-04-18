package com.example.docapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
public class ListActivity extends AppCompatActivity {

    private TextView orasTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        String oras = intent.getStringExtra("oras");;

        orasTxt = findViewById(R.id.orasTxt);
        orasTxt.setText("Selected city: " +  oras);
    }
}
