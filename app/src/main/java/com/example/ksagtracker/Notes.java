package com.example.ksagtracker;

import androidx.appcompat.app.AppCompatActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        n = (EditText)findViewById(R.id.Notes_text);
        save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes = n.getText().toString();
                Toast.makeText(getApplicationContext(),save_toast,Toast.LENGTH_LONG).show();
            }
        });
    }
}