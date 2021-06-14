package com.example.myquiz;

public class Singleton {
    private static Singleton instance;
    private String token;


    public void clearSingleton(){
        token = null;
    }
    public void SetToken(String token){
        this.token = token;
    }
    public String GetToken(){
        return token;
    }
    public static synchronized Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }


}
