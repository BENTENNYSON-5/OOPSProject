package com.example.ksagtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Dashboard_JS extends AppCompatActivity {
    TextView morejs;
    TextView searchjs;
    ImageButton jsmail;
    ImageButton jsinven;
    ImageButton jsgroc;
    TextView other2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard__js);
        morejs = (TextView)findViewById(R.id.morejs);
        searchjs = (TextView)findViewById(R.id.searchjs);
        jsmail = (ImageButton)findViewById(R.id.jsmail);
        jsinven = (ImageButton)findViewById(R.id.jsinven);
        jsgroc = (ImageButton)findViewById(R.id.jsgroc);
        other2 = (TextView)findViewById(R.id.other2);
        morejs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmoreoptions();
            }
        });
        searchjs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        jsmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        jsinven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        jsgroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        other2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              openAddCategory();
            }
        });
    }

    public void openmoreoptions() {
        Intent i = new Intent(this,moreoptions.class);
        startActivity(i);
    }
    public void openAddCategory(){
        Intent i = new Intent(this,AddCategory.class);
        startActivity(i);
    }
}