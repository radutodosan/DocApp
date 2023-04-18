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


public class ConnectMySql extends AsyncTask<String, Void, String> {
    String res = "";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(ListActivity.class, "Please wait...", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("Databaseection success");

            String result = "Database Connection Successful\n";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select distinct Country from tblCountries");
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                result += rs.getString(1).toString() + "\n";
            }
            res = result;
        } catch (Exception e) {
            e.printStackTrace();
            res = e.toString();
        }
        return res;
    }

    @Override
    protected void onPostExecute(String result) {
        txtData.setText(result);
    }
}