package com.example.myquiz.CreateTest;



import java.util.Map;

public interface CreateTestMethods {
    interface View {
        void save_test(Map<String, String> map);
        void showPd();
        void hidePd();
        void onResponseFailRule(String s);

    }
    interface Presenter{

        void sendPostToSaveTest(QuizDto quizDto_class, String token);
    }
    interface Model{
        interface OnFinishedListener{

            void onFinished(Map<String, String> map);
            void onFailure(String s);
        }

        void SaveTest(OnFinishedListener onFinishedListener, QuizDto quizDto_class, String token);
    }


}
