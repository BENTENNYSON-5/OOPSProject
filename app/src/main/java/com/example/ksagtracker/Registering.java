package com.example.ksagtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registering extends AppCompatActivity {
     Button verifyid,verifyno,Register;
     String newusername,emailid,cellno;
     EditText usrname,mailid,cellnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registering);
        verifyid= (Button)findViewById(R.id.verifyid);
        verifyno = (Button)findViewById(R.id.verifyno);
        Register = (Button)findViewById(R.id.newregister);
        usrname = (EditText)findViewById(R.id.newusername);
        mailid = (EditText)findViewById(R.id.newid);
        cellnum = (EditText)findViewById(R.id.newno);
        verifyid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newusername = usrname.getText().toString();
                emailid = mailid.getText().toString();
                //code akhil
            }
        });
        verifyno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cellno = cellnum.getText().toString();
                //code gagan
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code
                openDivisions();
            }
        });
    }
    public void openDivisions(){
        Intent i = new Intent(this,Divisions.class);
        i.putExtra("registered user",newusername);
        startActivity(i);
    }
}