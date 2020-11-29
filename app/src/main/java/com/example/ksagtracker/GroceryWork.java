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

public class GroceryWork extends Share implements TimePickerDialog.OnTimeSetListener , DatePickerDialog.OnDateSetListener {
    Calendar c;
    EditText Work1;
    EditText Date1;
    EditText Desc1;
    EditText item_name;
    String grocery_description_string;
    TextView mTextView1;
    Button save1;
    String name_item;
    String name_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_work);
        Button add1 = (Button) findViewById(R.id.button5);
        Button add2 = (Button) findViewById(R.id.button8);
        Button sub1 = (Button) findViewById(R.id.button6);
        Button sub2 = (Button) findViewById(R.id.button11);
        EditText num1=(EditText) findViewById(R.id.editTextNumberSigned2);
        EditText num2=(EditText) findViewById(R.id.editTextNumberSigned);
         Desc1 = findViewById(R.id.editTextTextPersonName5_grocery);


        Button gotodo_grocery = findViewById(R.id.gotodo_grocery);
        gotodo_grocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 item_name = findViewById(R.id.item_name);
                name_item = item_name.getText().toString();
                name_count = num1.getText().toString();

                openShare();
            }
        });
        Desc1 = findViewById(R.id.editTextTextPersonName5_grocery);
        save1 = findViewById(R.id.button7_grocery);
        Work1 = findViewById(R.id.editTextTextPersonName_grocery);
        Date1 = findViewById(R.id.editTextTextPersonName6_grocery);
        mTextView1 = findViewById(R.id.textView7_grocery);
        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stri1 = Work1.getText().toString()+"\n"+Date1.getText().toString()+"        "+mTextView1.getText().toString()+"\n"+Desc1.getText().toString();
                lisst.add(stri1);
                Messege.messege(getApplicationContext(),"Saved your Work. Now Click Back Button");
            }
        });

        Button buttonTimePicker = (Button) findViewById(R.id.button_grocery);
        Button buttonDate = (Button) findViewById(R.id.button14);
        Button buttonCancelAlarm = (Button) findViewById(R.id.button2_grocery);
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
        buttonCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num1.getText().toString().length() == 0) {
                    num1.setText("0");
                }
                int numb1 = Integer.parseInt(num1.getText().toString());
                int sum = numb1 + 1;
                num1.setText(String.valueOf(sum));
            }
        });
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num2.getText().toString().length() == 0) {
                    num2.setText("0");
                }
                int numb1 = Integer.parseInt(num2.getText().toString());
                int sum = numb1 + 1;
                num2.setText(String.valueOf(sum));
            }
        });
        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num1.getText().toString().length() == 0) {
                    num1.setText("0");
                }
                int numb1 = Integer.parseInt(num1.getText().toString());
                int sum = numb1 - 1;
                num1.setText(String.valueOf(sum));
            }
        });
        sub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num2.getText().toString().length() == 0) {
                    num2.setText("0");
                }
                int numb1 = Integer.parseInt(num2.getText().toString());
                int sum = numb1 - 1;
                num2.setText(String.valueOf(sum));
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView textView = (TextView) findViewById(R.id.editTextTextPersonName6_grocery);
        textView.setText(currentDateString);

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
        mTextView1.setText(timeText);
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
        mTextView1.setText("Alarm canceled");
    }
    private void openShare() {
        Intent in = new Intent(this,Share.class);
        in.putExtra("somestring",Desc1.getText().toString());
        in.putExtra("nameshare",Work1.getText().toString());
        in.putExtra("dateshare",Date1.getText().toString());
        in.putExtra("timeshare",mTextView1.getText().toString());
        in.putExtra("itemshare",name_item);
        in.putExtra("countshare",name_count);


        startActivity(in);
    }

}
