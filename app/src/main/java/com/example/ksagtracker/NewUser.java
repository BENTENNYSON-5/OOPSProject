package com.example.ksagtracker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewUser extends AppCompatActivity {
    String Username;
    String Emailaddress;
    Long Phoneno;

    ArrayList<String> mainlist;

    public NewUser() {
    }

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

    public Long getPhoneno() {
        return Phoneno;
    }

    public void setPhoneno(Long phoneno) {
        Phoneno = phoneno;
    }





    public ArrayList<String> getMainlist() {
        return mainlist;
    }

    public void setMainlist(ArrayList<String> mainlist) {
        this.mainlist = mainlist;
    }
}

