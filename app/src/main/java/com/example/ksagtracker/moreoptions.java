package com.example.ksagtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class moreoptions extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
Button todo;
Button notes;
Button logout;
Button dailytime;
TextView timing;
String note = getIntent().getStringExtra("previous notes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreoptions);
        todo = (Button)findViewById(R.id.todo);
        notes = (Button)findViewById(R.id.notes);
        logout = (Button)findViewById(R.id.logout);
        dailytime = (Button)findViewById(R.id.dailytime);
        timing = (TextView)findViewById(R.id.timing);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });
        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  openNotes();
            }
        });
        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dailytime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void startMainActivity() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timing.setText( hourOfDay + ":" + minute);
    }
    public void openNotes(){
        Intent i = new Intent(this,Notes.class);
        i.putExtra("my previous",note);
        startActivity(i);
    }
}