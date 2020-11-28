package com.example.ksagtracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class Share extends Listing {
    private String stringFile = Environment.getExternalStorageDirectory().getPath() + File.separator + "Test.pdf";
    public String shdes;
    public String fades;
    public String raids;
    public String times;
    public String itemss ;
    public String numberss ;

    Button share_description;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        share_description = findViewById(R.id.share_description);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        shdes = getIntent().getStringExtra("somestring");
        fades = getIntent().getStringExtra("nameshare");
        raids = getIntent().getStringExtra("dateshare");
        times = getIntent().getStringExtra("timeshare");
        if(itemss == null ){
            itemss = " ";
        }
        else{
            itemss = getIntent().getStringExtra("itemshare");
        }
        if(numberss == null){
            numberss = " ";
        }
        else
        {
        numberss = getIntent().getStringExtra("countshare");
        }
        TextView name_sharedetails = findViewById(R.id.name_sharedetails);
        name_sharedetails.setText(fades);
        TextView date_sharedetails = findViewById(R.id.date_sharedetails);
        date_sharedetails.setText(raids);
        TextView time_sharedetails = findViewById(R.id.time_sharedetails);
        time_sharedetails.setText(times);
        TextView description_sharedetails = findViewById(R.id.description_sharedetails);
        description_sharedetails.setText(itemss+" "+numberss+"\n" +shdes);


    }




    public void buttonShareText(View view){
        this.view = view;
        Intent intentShare = new Intent(Intent.ACTION_SEND);
        intentShare.setType("text/plain");
        intentShare.putExtra(Intent.EXTRA_SUBJECT,fades + "'s Description");
        intentShare.putExtra(Intent.EXTRA_TEXT,fades + "\n" +raids+ "\n"+times+"\n"+ itemss+" "+numberss+"\n" +shdes );

        startActivity(Intent.createChooser(intentShare, "Shared the text ..."));
    }
}
