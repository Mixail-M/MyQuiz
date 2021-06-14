package com.example.myquiz.PlayByCodeTest;

import java.util.ArrayList;

public class QuizResultDto {
    String id_quiz;
    ArrayList<QuestionResultDto> questionResultDtoList;
    public QuizResultDto() {
        this.id_quiz="";
        this.questionResultDtoList = new ArrayList<>();
    }
    public QuizResultDto(String id, ArrayList<QuestionResultDto> questionResultDtoList) {
        this.id_quiz=id;
        this.questionResultDtoList = questionResultDtoList;
    }
}
