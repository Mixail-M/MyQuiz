package com.example.myquiz.Registration;


public class RegistrationClass {
    String email;
    String password;
    String fio;
    //ProfileClass profilePerson;

    public RegistrationClass(String email,String password,String fio) {
        this.email = email;
        this.password = password;
        this.fio = fio;
        //this.profilePerson = profile;
    }
    public RegistrationClass(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
