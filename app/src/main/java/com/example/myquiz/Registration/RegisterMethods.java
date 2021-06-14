package com.example.myquiz.Registration;


import java.util.Map;

public interface RegisterMethods {
    interface View{
        void toLoginActivity(Map<String, String> map);
        void showPd();
        void hidePd();
        void onResponseFailRule(String t);
    }

    interface Presenter{
        void sendPostToRegisterUser(RegistrationClass registrationClass);
    }

    interface Model{
        interface OnFinishedListener{
            void onFinished(Map<String, String> map);
            void onFailure(String t);
        }
        void registerUser(OnFinishedListener onFinishedListener, RegistrationClass registrationClass);
    }

}
