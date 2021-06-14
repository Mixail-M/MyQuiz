package com.example.myquiz.Profile.Result;



import com.example.myquiz.Profile.GetMyTestMethods;
import com.example.myquiz.Profile.QuizProfile;

import java.util.ArrayList;

public class GetMyTestResultsPresenter implements GetMyTestResultsMethods.GetMyTestResultsPresentser, GetMyTestResultsMethods.GetMyTestResultsModel.OnFinishedListener {
    GetMyTestResultsMethods.GetMyTestResultsModel model;
    GetMyTestResultsMethods.GetMyTestResultsView view;


    public GetMyTestResultsPresenter(GetMyTestResultsMethods.GetMyTestResultsModel model, GetMyTestResultsMethods.GetMyTestResultsView view) {
        this.model = model;
        this.view = view;
    }
    @Override
    public void onFinish(ArrayList<ProfileRezClass> profileRezClasses) {
        view.showTest(profileRezClasses);
    }

    @Override
    public void onFailure() {
        view.showError();
    }

    @Override
    public void setToServer() {
        model.getMyTestResults(this);
    }
}
