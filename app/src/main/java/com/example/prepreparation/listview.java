package com.example.prepreparation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class listview extends AppCompatActivity {
  TextView vehicleno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        vehicleno = (TextView) findViewById(R.id.vehicleno);

    }
}