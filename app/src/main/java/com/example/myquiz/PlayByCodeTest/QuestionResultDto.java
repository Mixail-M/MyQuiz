package com.example.myquiz.PlayByCodeTest;

import java.util.ArrayList;

public class QuestionResultDto {
    String id_question;
    ArrayList<String> answer_id;
    String answer;
    public QuestionResultDto() {
        this.id_question="";
        this.answer_id = new ArrayList<>();
        this.answer="";
    }
    public QuestionResultDto(String id_question, ArrayList<String> answer_id, String answer) {
        this.id_question=id_question;
        this.answer_id = answer_id;
        this.answer=answer;
    }
}
