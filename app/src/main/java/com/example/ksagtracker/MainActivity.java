package com.example.ksagtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button login,toRegister;
    EditText Username;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.login);
        toRegister = (Button)findViewById(R.id.registering);
        Username = (EditText)findViewById(R.id.username);
        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               username = Username.getText().toString();
               //a line is there
               //toast is in Registering.java
            }
        });
        toRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openRegistering();
            }
        });
    }
   public void openRegistering(){
             Intent i =new Intent(this,Registering.class);
             i.putExtra("user",username);
             startActivity(i);
    }
}
