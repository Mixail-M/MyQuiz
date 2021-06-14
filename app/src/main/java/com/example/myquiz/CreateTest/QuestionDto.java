package com.example.myquiz.CreateTest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class QuestionDto implements Parcelable {
    String title;
    List<String> answer = new ArrayList<>();
    List<String> answer_correct;
    public QuestionDto() {
        this.title ="";
        this.answer.add("");
        this.answer.add("");
        this.answer.add("");
        this.answer.add("");
        this.answer_correct =new ArrayList<>();
    }
    public QuestionDto(String title, List<String> answer, List<String> answer_correct) {
        this.title = title;
        this.answer = answer;
        this.answer_correct = answer_correct;
    }
    public QuestionDto(Parcel in) {
        this.title =in.readString();
        this.answer =in.createStringArrayList();
        this.answer_correct =in.createStringArrayList();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
        out.writeStringList(answer);
        out.writeStringList(answer_correct);
    }

    public static final Parcelable.Creator<QuestionDto> CREATOR
            = new Parcelable.Creator<QuestionDto>() {
        public QuestionDto createFromParcel(Parcel in) {
            return new QuestionDto(in);
        }

        public QuestionDto[] newArray(int size) {
            return new QuestionDto[size];
        }
    };
}
