package com.example.myquiz.PlayByCodeTest;



import java.util.ArrayList;

public interface PlayByCodeMethods {

    interface PlayByCodeView{
        void showTest(GetTestClass getTestClass);
        //void onFinishAddToFavor(ClassId classId);
        //void toSearchResultsFragment(ArrayList<SearchAnnouncementRes> searchAnnouncementRes);
        void showError();
    }
    interface PlayByCodePresentser{
        void setToServer(long id);
    }
    interface PlayByCodeModel{
        interface OnFinishedListener{

            void onFinish(GetTestClass getTestClass);
            void onFailure();
        }

        void getTestById(OnFinishedListener onFinishedListener, long id);
    }
}
