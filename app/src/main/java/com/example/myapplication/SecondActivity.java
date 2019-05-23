package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("key");
        Brewery brewery = gson.fromJson(strObj, Brewery.class);
        
    }



}
