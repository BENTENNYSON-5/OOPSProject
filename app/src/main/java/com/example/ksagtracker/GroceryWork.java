package com.example.ksagtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GroceryWork extends Share {


    EditText Work1;
    EditText Date1;
    EditText Desc1;
    String grocery_description_string;
    TextView mTextView1;
    Button save1;
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
        EditText desc1 = findViewById(R.id.editTextTextPersonName5_grocery);
        Button gotodo_grocery = findViewById(R.id.gotodo_grocery);
        gotodo_grocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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

    private void openShare() {
        Intent in = new Intent(this,Share.class);
        in.putExtra("somestring",Desc1.getText().toString());

        startActivity(in);
    }
}
