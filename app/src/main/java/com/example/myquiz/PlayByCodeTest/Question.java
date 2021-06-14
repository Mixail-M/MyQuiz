package com.example.myquiz.PlayByCodeTest;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.myquiz.CreateTest.QuestionDto;

import java.util.ArrayList;

public class Question implements Parcelable {
    String id;
    String question;
    ArrayList<Answer> answerList;
    public Question() {
        this.id="";
        this.question="";
        this.answerList =new ArrayList<>();
    }
    public Question(String id, String question, ArrayList<Answer> answerList) {
        this.id=id;
        this.question=question;
        this.answerList =answerList;
    }
    public Question(Parcel in) {
        this.id =in.readString();
        this.question =in.readString();
        this.answerList = in.createTypedArrayList(Answer.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(id);
        out.writeString(question);
        out.writeTypedList(answerList);
    }

    public static final Parcelable.Creator<Question> CREATOR
            = new Parcelable.Creator<Question>() {
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
