package com.example.ksagtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Divisions extends AppCompatActivity {
    RadioGroup rg;
    Button b;
    String di;
    RadioButton js;
    RadioButton hm;
    RadioButton o;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisions);
        js=(RadioButton) findViewById(R.id.js);
        hm = findViewById(R.id.hm);
        o = findViewById(R.id.o);
        /*
        Context context = getApplicationContext();
        String text = "Toast message";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/
        Toast t = Toast.makeText(getApplicationContext(), "Successfully registered as "+getIntent().getStringExtra("registered user"),Toast.LENGTH_SHORT);
        t.show();
        rg = (RadioGroup)findViewById(R.id.radio);
        b = (Button)findViewById(R.id.done1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int d = rg.getCheckedRadioButtonId();
                if (d == -1) {
                    Messege.messege(getApplicationContext(), "Select any division");
                } else if (js.isChecked()) {
                    di = "JOB SEEKER";
                    openDashboard_JS();
                } else if(hm.isChecked()){
                    di = "HOME MAKER";
                    openDashboard_WP();
                }  else if(o.isChecked()){
                    di = "OTHERS";
                    openDashboard_WP();
                }  else {
                    di = "WORKING PROFESSIONAL";
                    openDashboard_WP();
                }
            }
        });
    }

    public void openDashboard_WP(){
        Intent intent = new Intent(Divisions.this,Dashboard_WP.class);
        intent.putExtra("Division",di);
        startActivity(intent);
    }
    public void openDashboard_JS(){
        Intent intent2 = new Intent(Divisions.this,Dashboard_JS.class);
        intent2.putExtra("Division",di);
        startActivity(intent2);
    }
}