package com.example.ksagtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Divisions extends AppCompatActivity {
    Button done1;
    RadioButton wp,js,hm,o;
    String division;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisions);
        /*
        Context context = getApplicationContext();
        String text = "Toast message";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/
        Toast t = Toast.makeText(getApplicationContext(), "Successfully registered as "+getIntent().getStringExtra("registered user"),Toast.LENGTH_SHORT);
        t.show();
        done1 = (Button)findViewById(R.id.done1);
        wp = (RadioButton)findViewById(R.id.wp);
        js = (RadioButton)findViewById(R.id.js);
        hm = (RadioButton)findViewById(R.id.hm);
        o = (RadioButton)findViewById(R.id.o);

    }
}