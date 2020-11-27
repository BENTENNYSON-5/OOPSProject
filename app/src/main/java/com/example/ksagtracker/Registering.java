package com.example.ksagtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registering extends AppCompatActivity {
     Button verifyid,verifyno,Register;
     EditText usrname,mailid,cellnum;
     DatabaseReference reff;
     NewUser newUser;
     String newusername;
     long num = 0;
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
        newUser = new NewUser();
        reff = FirebaseDatabase.getInstance().getReference().child("User");
        verifyid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code akhil
            }
        });
        verifyno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code gagan
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code
                if(passwordValidates(usrname.getText().toString())) {
                    newusername = usrname.getText().toString().trim();
                    newUser.setUsername(usrname.getText().toString().trim());
                    newUser.setEmailaddress(mailid.getText().toString().trim());
                    Long phno = Long.parseLong(cellnum.getText().toString().trim());
                    newUser.setPhoneno(phno);
                    reff.child(newUser.Username).setValue(newUser);
                    openDivisions();
                }
                else{
                    Messege.messege(getApplicationContext(),"Match Username Conditions!!");
                }
            }
        });
    }
    public void openDivisions(){
        Intent i = new Intent(this,Divisions.class);
        i.putExtra("registered user",newusername);
        startActivity(i);
    }
    public boolean passwordValidates( String pass ) {
        int countt = 0;

        if( 8 <= pass.length() && pass.length() <= 32  )
        {
            if( pass.matches(".*\\d.*") )
                countt ++;
            if( pass.matches(".*[a-z].*") )
                countt ++;
            if( pass.matches(".*[A-Z].*") )
                countt ++;
            if(pass.matches(".*[(?=.*[@#$%^&+=])].*" ) )
            countt ++;
        }

        return countt == 4;
    }
}