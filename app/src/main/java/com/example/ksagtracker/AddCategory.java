package com.example.ksagtracker;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;

public class AddCategory extends Share implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    private TextView mTextView2;
    Calendar c;
    EditText Work2;
    EditText Date2;
    EditText Desc2;
    Button save2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        Button buttonDate2 = (Button) findViewById(R.id.button13);
        Desc2 = findViewById(R.id.Description);
        save2 = findViewById(R.id.button7);
        Work2 = findViewById(R.id.Works);
        Date2 = findViewById(R.id.Dates);
        mTextView2 = findViewById(R.id.textView7643);
        Button gotodo12 = findViewById(R.id.gotodo);
        gotodo12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                openShare();
            }
        });
        save2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stri2 = Work2.getText().toString()+"\n"+Date2.getText().toString()+"        "+mTextView2.getText().toString()+"\n"+Desc2.getText().toString();
                lisst.add(stri2);
                Messege.messege(getApplicationContext(),"Saved your Work. Now Click Back Button");
            }
        });

        Button buttonTimePicker =(Button) findViewById(R.id.button4);
        buttonDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        buttonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        Button buttonCancelAlarm = (Button) findViewById(R.id.button3);
        buttonCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });
    }
    private void openShare() {
        Intent in = new Intent(this,Share.class);
        in.putExtra("somestring",Desc2.getText().toString());
        in.putExtra("nameshare",Work2.getText().toString());
        in.putExtra("dateshare",Date2.getText().toString());
        in.putExtra("timeshare",mTextView2.getText().toString());


        startActivity(in);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        Date2.setText(currentDateString);

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        c = Calendar.getInstance();
        hourOfDay--;
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        startAlarm(c);
        hourOfDay++;
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        updateTimeText(c);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateTimeText(Calendar c) {
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        mTextView2.setText(timeText);
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
    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent2 = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent2, 0);
        alarmManager.cancel(pendingIntent);
        mTextView2.setText("Alarm canceled");
    }
}