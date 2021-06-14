package com.example.myquiz.PlayByCodeTest;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.myquiz.CreateTest.QuestionDto;

import java.util.ArrayList;

public class Answer implements Parcelable{
    String id;
    String answer;
    public Answer(String id, String answer) {
        this.id=id;
        this.answer=answer;
    }
    public Answer(Parcel in) {
        this.id =in.readString();
        this.answer =in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(id);
        out.writeString(answer);
    }

    public static final Parcelable.Creator<Answer> CREATOR
            = new Parcelable.Creator<Answer>() {
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };
}
