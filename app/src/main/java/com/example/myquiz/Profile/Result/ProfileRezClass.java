package com.example.myquiz.Profile.Result;

public class ProfileRezClass {
    int id;
    String title;
    String fio;
    String email;
    String results;
    public ProfileRezClass() {
        this.id = -1;
        this.fio="";
        this.email="";
        this.results="";
    }
    public ProfileRezClass(int id,String title, String fio, String email, String results) {
        this.id = id;
        this.title = title;
        this.fio=fio;
        this.email=email;
        this.results=results;
    }
}
