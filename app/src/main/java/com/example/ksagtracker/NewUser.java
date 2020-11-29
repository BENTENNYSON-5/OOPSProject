package com.example.ksagtracker;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.Object;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class NewUser {
   private String Username;
   private String Emailaddress;
   private String Phoneno;
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmailaddress() {
        return Emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        Emailaddress = emailaddress;
    }

    public String getPhoneno() { return Phoneno; }

    public void setPhoneno(String phoneno) { Phoneno = phoneno; }
}

