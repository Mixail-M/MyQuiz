package com.example.myquiz.Profile;

import com.example.myquiz.CreateTest.QuestionDto;

import java.util.ArrayList;

public class QuizProfile {
    int id;
    String title;
    String description;
    public QuizProfile() {
        this.id = -1;
        this.title ="";
        this.description="";
    }
    public QuizProfile(int id,String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
