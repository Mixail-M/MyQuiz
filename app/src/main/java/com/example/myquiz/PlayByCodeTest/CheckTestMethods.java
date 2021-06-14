package com.example.myquiz.PlayByCodeTest;



import com.example.myquiz.CreateTest.QuizDto;

import java.util.Map;

public interface CheckTestMethods {
    interface View {
        void check_test(Map<String, String> map);
        void onResponseFailRule(String s);

    }
    interface Presenter{

        void sendPostToCheckTest(QuizResultDto quizResultDto, String token);
    }
    interface Model{
        interface OnFinishedListener{

            void onFinished(Map<String, String> map);
            void onFailure(String s);
        }

        void CheckTest(OnFinishedListener onFinishedListener, QuizResultDto quizResultDto, String token);
    }


}
