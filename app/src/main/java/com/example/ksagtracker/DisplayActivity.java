package com.example.ksagtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {
    Button done_display;
    TextView name_display;
    TextView date_display;
    TextView time_display;
    TextView description_display;
        SearchObjects ob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        name_display = findViewById(R.id.name_display);
        date_display = findViewById(R.id.date_display);
        time_display = findViewById(R.id.time_display);
        description_display = findViewById(R.id.description_display);
        done_display = findViewById(R.id.done_display);
        done_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openSearch();
            }
        });
    }

    private void openSearch() {
        Intent i =new Intent(this,Search.class);
        startActivity(i);
    }
    DisplayActivity(){

    }
}