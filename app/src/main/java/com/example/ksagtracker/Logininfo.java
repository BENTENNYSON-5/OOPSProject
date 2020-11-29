package com.example.ksagtracker;

public class Logininfo {
     String Username;
     String Emailaddress;
     String Phoneno;

    public String gUsername() {
        return Username;
    }

    public void sUsername(String username) {
        Username = username;
    }

    public String gEmailaddress() {
        return Emailaddress;
    }

    public void sEmailaddress(String emailaddress) {
        Emailaddress = emailaddress;
    }

    public String gPhoneno() {
        return Phoneno;
    }

    public void sPhoneno(String phoneno) {
        Phoneno = phoneno;
    }
}
