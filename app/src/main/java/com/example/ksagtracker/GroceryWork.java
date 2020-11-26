package com.example.ksagtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GroceryWork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_work);
        Button add1 = (Button) findViewById(R.id.button5);
        Button add2 =(Button) findViewById(R.id.button8);
        Button sub1 = (Button) findViewById(R.id.button6);
        Button sub2 = (Button) findViewById(R.id.button11);
        EditText num1=(EditText) findViewById(R.id.editTextNumberSigned2);
        EditText num2=(EditText) findViewById(R.id.editTextNumberSigned);
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
}
