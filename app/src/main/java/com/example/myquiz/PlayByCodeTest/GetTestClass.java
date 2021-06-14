package com.example.myquiz.PlayByCodeTest;

import java.util.ArrayList;

public class GetTestClass {
    String id;
    String title;
    String description;
    ArrayList<Question> questionArrayList;
    public GetTestClass() {
        this.id = "";
        this.title ="";
        this.description="";
        this.questionArrayList =new ArrayList<>();
    }
    public GetTestClass(String id, String title, String description, ArrayList<Question> questionArrayList) {
        this.id = id;
        this.title =title;
        this.description=description;
        this.questionArrayList =questionArrayList;
    }
}
