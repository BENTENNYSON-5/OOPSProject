package com.example.ksagtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Notes extends AppCompatActivity {

    Button save;
    String save_toast = "Saved";
    EditText n;
    String notes;
    Button done2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        n = (EditText)findViewById(R.id.Notes_text);
        save = (Button)findViewById(R.id.save);
        done2 = (Button)findViewById(R.id.done2);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes = n.getText().toString();
                Toast.makeText(getApplicationContext(),save_toast,Toast.LENGTH_SHORT).show();
            }
        });
        done2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmoreoptions();
                n.setText(notes);
            }
        });
    }
    public void openmoreoptions(){
        Intent i = new Intent(this,moreoptions.class);
        startActivity(i);
    }
}