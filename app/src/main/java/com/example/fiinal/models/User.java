package com.example.fiinal.models;

public class User {
    private String nomet;
    private String phoneet;
    private String ncinet;
    private String emailet;

    private String passowrdet;

    public User() {
    }

    public User(String nomS, String phoneS, String ncinS, String emailS, String passowrdS) {
        this.nomet = nomS;
        this.phoneet = phoneS;
        this.ncinet = ncinS;
        this.emailet = emailS;
        this.passowrdet = passowrdS;
    }

    public String getNomet() {

        return nomet;
    }

    public void setNomet(String nomet) {

        this.nomet = nomet;
    }

    public String getPhoneet() {

        return phoneet;
    }

    public void setPhoneet(String phoneet) {

        this.phoneet = phoneet;
    }

    public String getNcinet() {

        return ncinet;
    }

    public void setNcinet(String ncinet) {

        this.ncinet = ncinet;
    }

    public String getEmailet() {

        return emailet;
    }

    public void setEmailet(String emailet) {

        this.emailet = emailet;
    }

    public String getPasswordet() {

        return passowrdet;
    }

    public void setPassowrdet(String passowrdet) {

        this.passowrdet = passowrdet;
    }

}



