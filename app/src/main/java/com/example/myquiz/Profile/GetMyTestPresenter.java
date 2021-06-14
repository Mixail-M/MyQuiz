package com.example.myquiz.Profile;



import java.util.ArrayList;

public class GetMyTestPresenter implements GetMyTestMethods.GetMyTestPresentser, GetMyTestMethods.GetMyTestModel.OnFinishedListener {
    GetMyTestMethods.GetMyTestModel model;
    GetMyTestMethods.GetMyTestView view;


    public GetMyTestPresenter(GetMyTestMethods.GetMyTestModel model, GetMyTestMethods.GetMyTestView view) {
        this.model = model;
        this.view = view;
    }
    @Override
    public void onFinish(ArrayList<QuizProfile> quizProfiles) {
        view.showTest(quizProfiles);
    }

    @Override
    public void onFailure() {
        view.showError();
    }

    @Override
    public void setToServer() {
        model.getMyTest(this);
    }
}
