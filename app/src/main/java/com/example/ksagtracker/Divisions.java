package com.example.ksagtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Divisions extends AppCompatActivity {
    RadioGroup rg;
    Button b;
    DatabaseReference reff;
    RadioButton js;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisions);
        js=(RadioButton) findViewById(R.id.js);
        Toast t = Toast.makeText(getApplicationContext(), "Successfully registered as "+getIntent().getStringExtra("registered user"),Toast.LENGTH_SHORT);
        t.show();
        rg = (RadioGroup)findViewById(R.id.radio);
        b = (Button)findViewById(R.id.done1);
        reff = FirebaseDatabase.getInstance().getReference().child("User");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int d = rg.getCheckedRadioButtonId();
                if (d == -1) {
                    Messege.messege(getApplicationContext(), "Select any division");
                } else if (js.isChecked()) {

                    openDashboard_JS();
                } else {

                    openDashboard_WP();
                }
            }
        });
    }
    public void openDashboard_WP(){
        Intent intent = new Intent(Divisions.this,Dashboard_WP.class);
        startActivity(intent);
    }
    public void openDashboard_JS(){
        Intent intent2 = new Intent(Divisions.this,Dashboard_JS.class);
        startActivity(intent2);
    }
}