package com.example.myquiz.Profile;


import java.util.ArrayList;

public interface GetMyTestMethods {

    interface GetMyTestView{
        void showTest(ArrayList<QuizProfile> quizProfiles);
        //void onFinishAddToFavor(ClassId classId);
        //void toSearchResultsFragment(ArrayList<SearchAnnouncementRes> searchAnnouncementRes);
        void showError();
    }
    interface GetMyTestPresentser{
        void setToServer();
    }
    interface GetMyTestModel{
        interface OnFinishedListener{

            void onFinish(ArrayList<QuizProfile> quizProfiles);
            void onFailure();
        }

        void getMyTest(OnFinishedListener onFinishedListener);
    }
}
