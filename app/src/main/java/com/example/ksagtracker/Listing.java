package com.example.ksagtracker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Listing extends Registering {
     public static ArrayList<String> lisst;
   //  String ttt;
   //  Objt ob;
   //  private DatabaseReference uu;
     static{
          lisst = new ArrayList<String>();
     }
/*
     public void uploadlist(){
          uu = FirebaseDatabase.getInstance().getReference().child(String.valueOf(newUser.getUsername()));
               for (int r = 0; r < lisst.size(); r++) {
                    ttt = lisst.get(r);
                    ob = new Objt();
                    ob.setSr(ttt.trim());
                    uu.child(String.valueOf(r+1)).setValue(ob);
                    }
     }*/
}
