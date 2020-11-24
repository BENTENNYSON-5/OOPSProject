package com.example.ksagtracker;

import android.content.Context;
import android.widget.Toast;

public class Messege {
    public static void messege(Context context,String m){
        Toast.makeText(context,m,Toast.LENGTH_SHORT).show();
    }
}
