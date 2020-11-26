package com.example.ksagtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Divisions extends AppCompatActivity {
    RadioGroup rg;
    Button b;
    String divi;
    RadioButton js;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisions);
        js=(RadioButton) findViewById(R.id.js);
        /*
        Context context = getApplicationContext();
        String text = "Toast message";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/
        Toast t = Toast.makeText(getApplicationContext(), "Successfully registered as "+getIntent().getStringExtra("registered user"),Toast.LENGTH_SHORT);
        t.show();
        rg = (RadioGroup)findViewById(R.id.radio);
        b = (Button)findViewById(R.id.done1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int d = rg.getCheckedRadioButtonId();
                if(d ==-1){
                    Messege.messege(getApplicationContext(),"Select any division");
                }
                else if(js.isChecked()) {
                    openDashboard_JS();
                }
                else {
                    openDashboard_WP();
                }
                }
        });
    }
  /*  public void onRadioButtonClicked(View view ){
        boolean checked = ((RadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.wp:
                if(checked) {
                    openDashboard_WP();
                }
            case R.id.js:
                if(checked){
                    openDashboard_JS();
                }
        }
    }*/
    public void openDashboard_WP(){
        Intent intent = new Intent(Divisions.this,Dashboard_WP.class);
        startActivity(intent);
    }
    public void openDashboard_JS(){
        Intent intent2 = new Intent(Divisions.this,Dashboard_JS.class);
        startActivity(intent2);
    }
}