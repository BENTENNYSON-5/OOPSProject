package com.example.ksagtracker;

public class NewUser {
    String Username;
    String Emailaddress;
    Long Phoneno;
    String Division;


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

    public String getDivision() {
        return Division;
    }

    public void setDivision(String division) {
        Division = division;
    }
}
