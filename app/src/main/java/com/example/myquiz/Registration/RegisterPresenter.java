package com.example.myquiz.Registration;


import java.util.Map;

public class RegisterPresenter implements RegisterMethods.Presenter, RegisterMethods.Model.OnFinishedListener {
    RegisterMethods.View view;
    RegisterMethods.Model model;

    public RegisterPresenter(RegisterMethods.View view, RegisterMethods.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void sendPostToRegisterUser(RegistrationClass registrationClass) {
        model.registerUser(this,registrationClass);
    }

    @Override
    public void onFinished(Map<String, String> map) {
        if(map!=null) {
            view.toLoginActivity(map);
        }else{

        }
    }

    @Override
    public void onFailure(String t) {
        view.onResponseFailRule(t);
    }
}
