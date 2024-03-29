package com.example.ksagtracker;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.telephony.SmsManager;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;

public class AddWork extends Share  implements TimePickerDialog.OnTimeSetListener , DatePickerDialog.OnDateSetListener {

     EditText Work;
     EditText Date;
     EditText Desc;
     private TextView mTextView;
     Calendar c;
     Button ok;
     Button share;
     DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);
        mTextView = findViewById(R.id.time);
        Button buttonTimePicker =(Button) findViewById(R.id.button);
        Button buttonDate = (Button) findViewById(R.id.button12);
        Work = findViewById(R.id.Work);
        Date = findViewById(R.id.Date);
        Desc = findViewById(R.id.Desc);
        ok = findViewById(R.id.button7);
        share = findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openShare();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stri = Work.getText().toString()+"\n"+Date.getText().toString()+"        "+mTextView.getText().toString()+"\n"+Desc.getText().toString();
                lisst.add(stri);
                Messege.messege(getApplicationContext(),"Saved your Work. Now Click Back Button");

            }
        });
        buttonDate.setOnClickListener(new View.OnClickListener() {
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
        Button buttonCancelAlarm = (Button) findViewById(R.id.button2);
        buttonCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });
    }

    private void openShare() {
        Intent in = new Intent(this,Share.class);
        in.putExtra("somestring",Desc.getText().toString());
        in.putExtra("nameshare",Work.getText().toString());
        in.putExtra("dateshare",Date.getText().toString());
        in.putExtra("timeshare",mTextView.getText().toString());

        startActivity(in);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        Date.setText(currentDateString);

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
        mTextView.setText(timeText);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }
    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        alarmManager.cancel(pendingIntent);
        mTextView.setText("Alarm canceled");
    }
}