package com.example.ksagtracker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class Share extends Listing {
    private String stringFile = Environment.getExternalStorageDirectory().getPath() + File.separator + "Test.pdf";
    public String shdes;
    public String fades;
    Button share_image;
    Button share_description;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        share_image = findViewById(R.id.share_image);
        share_description = findViewById(R.id.share_description);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        shdes = getIntent().getStringExtra("somestring");
        fades = getIntent().getStringExtra("registered user");
    }


    public void buttonShareFile(View view){
        File file = new File(stringFile);
        if (!file.exists()){
            Toast.makeText(this, "File doesn't exists", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intentShare = new Intent(Intent.ACTION_SEND);
        intentShare.setType("image/*");
        intentShare.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+file));
        startActivity(Intent.createChooser(intentShare, "Share the file ..."));
    }

    public void buttonShareText(View view){
        this.view = view;
        Intent intentShare = new Intent(Intent.ACTION_SEND);
        intentShare.setType("text/plain");
        intentShare.putExtra(Intent.EXTRA_SUBJECT,fades + "'s Description");
        intentShare.putExtra(Intent.EXTRA_TEXT,fades + "\r\n" + shdes );

        startActivity(Intent.createChooser(intentShare, "Shared the text ..."));
    }
}