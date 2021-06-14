package com.example.myquiz.Profile.Result;


import com.example.myquiz.Profile.QuizProfile;

import java.util.ArrayList;

public interface GetMyTestResultsMethods {

    interface GetMyTestResultsView{
        void showTest(ArrayList<ProfileRezClass> profileRezClasses);
        //void onFinishAddToFavor(ClassId classId);
        //void toSearchResultsFragment(ArrayList<SearchAnnouncementRes> searchAnnouncementRes);
        void showError();
    }
    interface GetMyTestResultsPresentser{
        void setToServer();
    }
    interface GetMyTestResultsModel{
        interface OnFinishedListener{

            void onFinish(ArrayList<ProfileRezClass> profileRezClasses);
            void onFailure();
        }

        void getMyTestResults(OnFinishedListener onFinishedListener);
    }
}
