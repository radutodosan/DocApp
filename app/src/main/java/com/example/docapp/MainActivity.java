package com.example.docapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView orasTxt;

    private Button nextBtn;

    String oras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orasTxt = findViewById(R.id.cityTxt);

        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (orasTxt.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Enter a city", Toast.LENGTH_SHORT).show();
                }
                else {
                    oras = orasTxt.getText().toString();
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    intent.putExtra("oras", oras);
                    startActivityForResult(intent, 1);
                }
            }
        });
    }
}