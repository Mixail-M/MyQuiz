package com.example.myquiz.CreateTest;

import java.util.ArrayList;
import java.util.List;

public class QuizDto {
    String title;
    String description;
    ArrayList<QuestionDto> question;
    public QuizDto() {
        this.title ="";
        this.description="";
        this.question =new ArrayList<>();
    }
    public QuizDto(String title, String description, ArrayList<QuestionDto> question) {
        this.title = title;
        this.description = description;
        this.question = question;
    }
}
