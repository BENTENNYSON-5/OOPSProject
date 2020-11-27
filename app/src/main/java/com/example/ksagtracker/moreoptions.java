package com.example.ksagtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import android.content.Context;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.icu.util.Calendar;
import android.os.Build;


import java.text.DateFormat;

public class moreoptions extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
Button todo;
Button notes;
Button logout;
Button dailytime;
TextView timing;
    private TextView mTextView3;

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
            openToDo();
            }
        });
        dailytime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mTextView3 = findViewById(R.id.timing);
        Button buttonTimePicker =(Button) findViewById(R.id.dailytime);
        buttonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });



    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        updateTimeText(c);
        startAlarm(c);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateTimeText(Calendar c) {
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        mTextView3.setText(timeText);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    public void startMainActivity() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }



    public void openNotes(){
        Intent i = new Intent(this,Notes.class);
        startActivity(i);
    }
    public void openToDo(){
        Intent i = new Intent(this,ToDo.class);
        startActivity(i);
    }

}
/*sdfsdgsdgf*/