package com.example.myquiz.PlayByCodeTest;


import java.util.ArrayList;

public class PlayByCodePresenter implements PlayByCodeMethods.PlayByCodePresentser, PlayByCodeMethods.PlayByCodeModel.OnFinishedListener {
    PlayByCodeMethods.PlayByCodeModel model;
    PlayByCodeMethods.PlayByCodeView view;


    public PlayByCodePresenter(PlayByCodeMethods.PlayByCodeModel model, PlayByCodeMethods.PlayByCodeView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onFinish(GetTestClass getTestClass) {
        view.showTest(getTestClass);
    }

    @Override
    public void onFailure() {
        view.showError();
    }

    @Override
    public void setToServer(long id) {
        model.getTestById(this,id);
    }
}
