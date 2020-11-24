package com.example.ksagtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Dashboard_WP extends AppCompatActivity {
    TextView morewp;
    TextView searchwp;
    ImageButton wpmeet;
    ImageButton wpinven;
    ImageButton wpbill;
    TextView other1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard__w_p);
        morewp = (TextView)findViewById(R.id.morewp);
        searchwp = (TextView)findViewById(R.id.searchwp);
        wpmeet = (ImageButton)findViewById(R.id.wpmeet);
        wpinven = (ImageButton)findViewById(R.id.wpinven);
        wpbill = (ImageButton)findViewById(R.id.wpbill);
        other1 = (TextView)findViewById(R.id.other1);
        morewp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              openmoreoptions();
            }
        });
        searchwp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        wpmeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        wpinven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        wpbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        other1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              openAddCategory();
            }
        });
    }
    public void openmoreoptions(){
        Intent i = new Intent(this,moreoptions.class);
        startActivity(i);
    }
    public void openAddCategory(){
        Intent i = new Intent(this,AddCategory.class);
        startActivity(i);
    }
}